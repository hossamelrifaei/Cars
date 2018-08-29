package data

import di.ActivityScope
import io.reactivex.Maybe
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import model.CarsResponse
import javax.inject.Inject
import javax.inject.Provider

/**
 * Created by hossam
 */
@ActivityScope
class CarsRepo @Inject internal constructor(private val apiRequesterProvider: Provider<ApiRequester>) {

    private var cachedCars: CarsResponse? = null

    fun getCars(): Single<CarsResponse> {
        return Maybe.concat<CarsResponse>(getCashed(), apiCars())
                .firstOrError()
                .subscribeOn(Schedulers.io())
    }

    private fun getCashed(): Maybe<CarsResponse>? {
        return Maybe.create<CarsResponse> { e ->
            cachedCars?.let { e.onSuccess(it) }
            e.onComplete()
        }
    }

    private fun apiCars(): Maybe<CarsResponse> {
        return apiRequesterProvider.get().getCars()
                .doOnSuccess { data -> cachedCars = data }
                .toMaybe()
    }
}