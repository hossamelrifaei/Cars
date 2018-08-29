package ui.screens.map

import com.google.android.gms.maps.model.LatLng
import data.CarsRepo
import data.LocationService
import di.FragmentScope
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import model.MapMarkerItem
import model.PlaceMark
import ui.DisposableManager
import ui.RouterImpl
import javax.inject.Inject

/**
 * Created by hossam
 */
@FragmentScope
class MapPresenterImpl @Inject internal constructor(private val carsRepo: CarsRepo, private val disposableManager: DisposableManager, private val locationService: LocationService, private val router: RouterImpl) : MapContract.Presenter {
    private lateinit var view: MapContract.View
    override fun listClicked() {
        router.goToList()
    }

    override fun mapIsReady() {
        disposableManager.add(locationService.getLocation()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap { latLng ->
                    view.prepareMap(latLng)
                    carsRepo.getCars()
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response -> response.placemarks.forEach { view.addMarker(getMarker(it)) } }
                        , { _ -> })
        )
    }

    private fun getMarker(placeMark: PlaceMark): MapMarkerItem {
        return MapMarkerItem(LatLng(placeMark.coordinates[1], placeMark.coordinates[0]), placeMark.name)
    }

    override fun attach(view: MapContract.View) {
        this.view = view
        view.init()
    }

    override fun detach() {
        disposableManager.dispose()
    }
}