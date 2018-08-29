package ui.screens.map

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import base.BaseFragment
import com.cars.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.algo.GridBasedAlgorithm
import com.google.maps.android.clustering.algo.PreCachingAlgorithmDecorator
import kotlinx.android.synthetic.main.fragment_map.*
import model.MapMarkerItem
import javax.inject.Inject


/**
 * Created by hossam
 */
private const val ZOOM_LEVEL = 15f

class MapFragment : BaseFragment(), MapContract.View, OnMapReadyCallback {
    companion object {
        @JvmStatic
        fun newInstance(): MapFragment {
            return MapFragment()
        }
    }

    private lateinit var clusterManager: ClusterManager<MapMarkerItem>
    @Inject
    lateinit var presenter: MapPresenterImpl
    private lateinit var gmap: GoogleMap

    override fun addMarker(markerItem: MapMarkerItem) {
        clusterManager.addItem(markerItem)
    }


    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap?) {
        googleMap?.let {
            gmap = googleMap
            presenter.mapIsReady()

        }
    }

    @SuppressLint("MissingPermission")
    override fun prepareMap(latLng: LatLng) {
        clusterManager = ClusterManager(context, gmap)
        clusterManager.algorithm = PreCachingAlgorithmDecorator<MapMarkerItem>(GridBasedAlgorithm<MapMarkerItem>())
        gmap.setOnCameraIdleListener(clusterManager)
        gmap.setOnMarkerClickListener(clusterManager)
        gmap.isMyLocationEnabled = true
        gmap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, ZOOM_LEVEL))
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapview?.onCreate(savedInstanceState)
        presenter.attach(this)

    }

    override fun init() {
        mapview?.getMapAsync(this)
        fab.setOnClickListener { presenter.listClicked() }
    }


    override fun layoutRes(): Int {
        return R.layout.fragment_map
    }

    override fun onResume() {
        super.onResume()
        mapview?.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapview?.onPause()
    }

    override fun detach() {
        presenter.detach()
        mapview?.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapview?.onLowMemory()
    }
}