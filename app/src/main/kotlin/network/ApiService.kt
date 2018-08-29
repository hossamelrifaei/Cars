package network

import io.reactivex.Single
import model.CarsResponse
import retrofit2.http.GET

/**
 * Created by hossam
 */

interface ApiService {
    @GET("wunderbucket/locations.json")
    fun getCars(): Single<CarsResponse>
}