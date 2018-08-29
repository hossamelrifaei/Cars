package di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import home.HomeModule
import home.MainActivity

/**
 * Created by hossam
 */
@Module
abstract class ActivityBindingModule {
    @ActivityScope
    @ContributesAndroidInjector(modules = [HomeModule::class])
    internal abstract fun mainActivity(): MainActivity
}