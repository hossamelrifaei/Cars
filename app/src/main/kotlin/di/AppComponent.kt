package di

import base.CarsApp
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import data.CarsServiceModule
import network.ServiceModule
import javax.inject.Singleton

/**
 * Created by hossam
 */

@Singleton
@Component(modules = [(AndroidSupportInjectionModule::class)
    , (AppModule::class)
    , (ActivityBindingModule::class)
    , (ServiceModule::class)
    , (CarsServiceModule::class)])
interface AppComponent {
    fun inject(app: CarsApp)
}