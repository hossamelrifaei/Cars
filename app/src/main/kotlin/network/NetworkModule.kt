package network

import dagger.Module
import dagger.Provides
import okhttp3.Call
import okhttp3.OkHttpClient
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by hossam
 */
@Module
object NetworkModule {
    @JvmStatic
    @Provides
    @Singleton
    fun provideOkHttp(): Call.Factory = OkHttpClient.Builder().build()

    @JvmStatic
    @Provides
    @Named("base_url")
    fun provideBaseUrl(): String = "https://s3-us-west-2.amazonaws.com/"

}