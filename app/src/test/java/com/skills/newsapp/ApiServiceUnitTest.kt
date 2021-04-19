package com.skills.newsapp

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.skills.newsapp.data.remote.ApiService
import com.skills.newsapp.data.remote.RequestInterceptor
import com.skills.newsapp.utils.AppConstants
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

@RunWith(JUnit4::class)
class ApiServiceUnitTest {
    private var apiService: ApiService? = null
    @Before
    fun createService() {
        val client = OkHttpClient().newBuilder()
        client.connectTimeout(AppConstants.CONNECT_TIMEOUT, TimeUnit.MINUTES)
        client.readTimeout(AppConstants.READ_TIMEOUT, TimeUnit.MINUTES)
        client.writeTimeout(AppConstants.WRITE_TIMEOUT, TimeUnit.MINUTES)
        client.addInterceptor(RequestInterceptor())
        client.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        apiService = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client.build())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            .create(ApiService::class.java)
    }

    @Test
    fun `should check getting response from article list api`()=
        try {
            runBlocking  {
                val response = apiService?.getArticles(7)?.await()
                Assert.assertNotNull(response?.items)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }

    @After
    fun tearDown() {
        // Clean up resources here
    }
}