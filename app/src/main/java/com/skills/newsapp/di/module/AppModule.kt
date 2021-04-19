package com.skills.newsapp.di.module

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.skills.newsapp.data.ArticleDataSource
import com.skills.newsapp.data.ArticleDetailsDataSource
import com.skills.newsapp.data.remote.ApiService
import com.skills.newsapp.data.repository.ArticleDetailsRepository
import com.skills.newsapp.data.repository.ArticleRepository
import com.skills.newsapp.BuildConfig
import com.skills.newsapp.data.local.AppDatabase
import com.skills.newsapp.data.remote.RequestInterceptor
import com.skills.newsapp.utils.AppConstants
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
/**
 *  @author Varun Jain
 *  @Created  16-04-2021
 *  @Modified 16-04-2021
 * */
@Module
class AppModule {
    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @Singleton
    fun provideRetrofitClient():OkHttpClient{
        return OkHttpClient().newBuilder()
                .connectTimeout(AppConstants.CONNECT_TIMEOUT, TimeUnit.MINUTES)
                .writeTimeout(AppConstants.WRITE_TIMEOUT, TimeUnit.MINUTES)
                .readTimeout(AppConstants.READ_TIMEOUT, TimeUnit.MINUTES)
                .addInterceptor(RequestInterceptor())
                .addInterceptor(loggingInterceptor())
                .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client:OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create<ApiService>(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideAppDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, AppConstants.DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideArticleDataSource(ArticleRepository: ArticleRepository): ArticleDataSource {
        return ArticleRepository
    }

    @Provides
    @Singleton
    fun provideArticleDetailsDataSource(ArticleDetailsRepository: ArticleDetailsRepository): ArticleDetailsDataSource {
        return ArticleDetailsRepository
    }

    private fun loggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.HEADERS
    }
}