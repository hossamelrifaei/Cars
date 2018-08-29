package home

import android.Manifest
import android.os.Build
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import base.BaseActivity
import com.cars.R
import ui.screens.map.MapFragment
import javax.inject.Inject

class MainActivity : BaseActivity(), HomeContract.View {

    @Inject
    lateinit var presenter: HomePresenterImpl

    override fun requestPermission() {
        ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION)
    }

    override fun detach() {
        presenter.detach()
    }

    override fun initialScreen(): Fragment {
        return MapFragment.newInstance()
    }

    override fun checkForPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            presenter.grantPermission()
        } else {
            presenter.load()
        }
    }


    override fun layoutRes(): Int {
        return R.layout.activity_main
    }

    override fun attachView() {
        presenter.attach(this)
        presenter.load()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        presenter.permissionResult(requestCode, grantResults)
    }

}
