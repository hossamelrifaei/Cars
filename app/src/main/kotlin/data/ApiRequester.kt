package data

import io.reactivex.Single
import model.CarsResponse
import network.ApiService
import javax.inject.Inject

/**
 * Created by hossam
 */
class ApiRequester @Inject internal constructor(private val apiService: ApiService) {
    fun getCars(): Single<CarsResponse> {
        return apiService.getCars()
    }
}