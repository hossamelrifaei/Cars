package ui.screens.list

import data.CarsRepo
import di.FragmentScope
import io.reactivex.android.schedulers.AndroidSchedulers
import ui.DisposableManager
import ui.RouterImpl
import javax.inject.Inject

/**
 * Created by hossam
 */
@FragmentScope
class CarsListPresenterImpl @Inject internal constructor(private val carsRepo: CarsRepo, private val disposableManager: DisposableManager, private val router: RouterImpl) : CarsListContract.Presenter {
    private lateinit var view: CarsListContract.View
    override fun attach(view: CarsListContract.View) {
        this.view = view
        view.init()
    }

    override fun detach() {
        disposableManager.dispose()
    }

    override fun mapClicked() {
        router.goToMap()
    }

    override fun start() {
        disposableManager.add(carsRepo.getCars()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ carsResponse -> view.populateList(carsResponse.placemarks) }
                        , { _ -> }))
    }
}