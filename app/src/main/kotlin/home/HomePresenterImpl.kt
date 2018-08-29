package home

import data.CarsRepo
import di.ActivityScope
import ui.DisposableManager
import javax.inject.Inject

/**
 * Created by hossam
 */
const val PERMISSION_GRANTED = 0
const val PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1

@ActivityScope
class HomePresenterImpl @Inject internal constructor(private val carsRepo: CarsRepo, private val disposableManager: DisposableManager) : HomeContract.Presenter {

    private lateinit var view: HomeContract.View

    override fun detach() {
        disposableManager.dispose()
    }

    override fun attach(view: HomeContract.View) {
        this.view = view
    }

    override fun grantPermission() {
        view.requestPermission()
    }

    override fun load() {
        disposableManager.add(carsRepo.getCars()
                .subscribe())
    }

    override fun permissionResult(requestCode: Int, grantResults: IntArray) {
        when (requestCode) {
            PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PERMISSION_GRANTED) {
                    load()
                }
            }
        }
    }
}