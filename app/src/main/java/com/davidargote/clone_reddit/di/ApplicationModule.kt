package com.davidargote.clone_reddit.di

import android.content.Context
import com.davidargote.clone_reddit.core.Constants
import com.davidargote.clone_reddit.data.local.ApplicationDatabase
import com.davidargote.clone_reddit.data.remote.Service
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Singleton
    @Provides
    fun provideClient(): Service = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL_REDDIT_SERVICE)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .build().create(Service::class.java)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = ApplicationDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun providePostDao(db: ApplicationDatabase) = db.postDao()

}