package extensions

import android.location.Location
import com.google.android.gms.maps.model.LatLng

/**
 * Created by hossam
 */
fun Location.toLatLng(): LatLng {
    return LatLng(this.latitude, this.longitude)
}