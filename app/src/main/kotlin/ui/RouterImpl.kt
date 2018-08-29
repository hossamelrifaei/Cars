package ui

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import com.cars.R
import di.ActivityScope
import ui.screens.list.ListFragment
import ui.screens.map.MapFragment
import javax.inject.Inject

/**
 * Created by hossam
 */
@ActivityScope
class RouterImpl @Inject internal constructor() : Router {
    private lateinit var fragmentManager: FragmentManager

    fun onCreate(activity: AppCompatActivity, initialScreen: Fragment) {
        init(activity.supportFragmentManager, initialScreen)
    }

    private fun init(fragmentManager: FragmentManager, rootScreen: Fragment) {
        this.fragmentManager = fragmentManager
        if (fragmentManager.fragments.size == 0) {
            fragmentManager.beginTransaction()
                    .replace(R.id.screen_container, rootScreen)
                    .commit()
        }
    }

    override fun goToMap() {
        fragmentManager.beginTransaction()
                .replace(R.id.screen_container, MapFragment.newInstance())
                .commit()
    }

    override fun goToList() {
        fragmentManager.beginTransaction()
                .replace(R.id.screen_container, ListFragment.newInstance())
                .commit()
    }
}