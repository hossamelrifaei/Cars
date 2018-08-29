package ui.screens.map

import com.google.android.gms.maps.model.LatLng
import model.MapMarkerItem

/**
 * Created by hossam
 */
interface MapContract {
    interface Presenter {
        fun attach(view: MapContract.View)
        fun detach()
        fun mapIsReady()
        fun listClicked()
    }

    interface View {
        fun init()
        fun prepareMap(latLng: LatLng)
        fun addMarker(markerItem: MapMarkerItem)
    }
}