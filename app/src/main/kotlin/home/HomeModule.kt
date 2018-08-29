package home

import dagger.Module
import dagger.android.ContributesAndroidInjector
import di.FragmentScope
import ui.screens.list.ListFragment
import ui.screens.map.MapFragment

/**
 * Created by hossam
 */
@Module
abstract class HomeModule {
    @FragmentScope
    @ContributesAndroidInjector
    internal abstract fun provideMapFragment(): MapFragment

    @FragmentScope
    @ContributesAndroidInjector
    internal abstract fun provideListFragment(): ListFragment
}