package di

import android.content.Context
import base.CarsApp
import dagger.Module
import dagger.Provides
import data.LocationService
import javax.inject.Singleton

/**
 * Created by hossam
 */
@Module
class AppModule(private val app: CarsApp) {
    @Provides
    fun provideApplication(app: CarsApp): Context = app

    @Provides
    @Singleton
    fun provideLocationService(): LocationService = LocationService(app)
}