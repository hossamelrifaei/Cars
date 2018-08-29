package base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import ui.RouterImpl
import javax.inject.Inject


/**
 * Created by hossam
 */
abstract class BaseActivity : AppCompatActivity(), HasSupportFragmentInjector {
    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>
    @Inject
    lateinit var router: RouterImpl


    abstract fun detach()
    abstract fun attachView()
    abstract fun checkForPermission()
    abstract fun layoutRes(): Int
    abstract fun initialScreen(): Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(layoutRes())
        attachView()
        checkForPermission()
        router.onCreate(this, initialScreen())
    }

    override fun onDestroy() {
        super.onDestroy()
        detach()
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment>? {
        return fragmentDispatchingAndroidInjector
    }
}