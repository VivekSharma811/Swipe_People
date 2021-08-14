package com.example.swipepeople.hilt

import android.content.Context
import com.example.swipepeople.data.Dao
import com.example.swipepeople.data.PeopleDb
import com.example.swipepeople.network.ApiService
import com.example.swipepeople.network.datasource.PeopleDatasource
import com.example.swipepeople.network.datasource.PeopleDatasourceImpl
import com.example.swipepeople.repository.PeopleRepository
import com.example.swipepeople.repository.PeopleRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.reactivex.disposables.CompositeDisposable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PeopleModule {

    @Singleton
    @Provides
    fun provideBaseUrl() = "https://randomuser.me/"

    @Singleton
    @Provides
    fun provideOkHttpClient() = OkHttpClient.Builder()
        .connectTimeout(120, TimeUnit.SECONDS).readTimeout(120, TimeUnit.SECONDS).build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL: String): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit) = retrofit.create(ApiService::class.java)

    @Singleton
    @Provides
    fun provideCompositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) = PeopleDb.getDatabase(context)

    @Singleton
    @Provides
    fun provideDao(db: PeopleDb) = db.dao()

    @Singleton
    @Provides
    fun providePeopleDatasource(
        disposable: CompositeDisposable,
        apiService: ApiService
    ): PeopleDatasource {
        return PeopleDatasourceImpl(disposable, apiService)
    }

    @Singleton
    @Provides
    fun providePeopleRepository(peopleDatasource: PeopleDatasource, dao: Dao): PeopleRepository {
        return PeopleRepositoryImpl(peopleDatasource, dao)
    }

}