package cc.anisimov.vlad.simplealbumlist.di.module

import android.content.Context
import android.content.res.Resources
import cc.anisimov.vlad.simplealbumlist.data.source.remote.TypicodeApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class AppModule {
    companion object{
        const val BASE_URL = "https://jsonplaceholder.typicode.com"
    }

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): TypicodeApi {
        return retrofit.create(TypicodeApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideResources(@ApplicationContext applicationContext: Context): Resources {
        return applicationContext.resources
    }
}