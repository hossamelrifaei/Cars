package data

import android.annotation.SuppressLint
import android.content.Context
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import extensions.toLatLng
import io.reactivex.Single

/**
 * Created by hossam
 */
open class LocationService
constructor(context: Context) {

    private val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)

    @SuppressLint("MissingPermission")
    fun getLocation(): Single<LatLng> {
        return Single.create { singleSubscriber ->
            fusedLocationProviderClient.lastLocation.addOnCompleteListener { task ->
                if (task.isSuccessful && task.result != null) {
                    return@addOnCompleteListener singleSubscriber.onSuccess(task.result.toLatLng())
                } else {
                    return@addOnCompleteListener singleSubscriber.onError(Throwable("Error"))
                }
            }
        }
    }


}









