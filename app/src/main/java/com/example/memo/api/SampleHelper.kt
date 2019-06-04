package com.example.memo.api


//これがサーバの２つ前





import android.content.Context
import com.example.memo.BuildConfig
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import jp.co.eastem.sample.api.SampleApiClient
import jp.co.eastem.sample.api.service.SampleApiService
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

object SampleHelper {

    private val CACHE_FILE_NAME = "okhttp.cache"
    private val MAX_CACHE_SIZE = (4 * 1024 * 1024).toLong()
    private val LOG_LEVEL: HttpLoggingInterceptor.Level = HttpLoggingInterceptor.Level.BODY

    fun getSampleApiClient(context: Context) : SampleApiClient {
        val helper = this
        val http = helper.provideHttpClient(context)
        val api = helper.provideSampleApiService(http)
        val client = SampleApiClient(api)
        return client
    }

    private fun provideHttpClient(context: Context): OkHttpClient {
        val cacheDir = File(context.cacheDir, CACHE_FILE_NAME)
        val cache = Cache(cacheDir, MAX_CACHE_SIZE)

        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = LOG_LEVEL

        val c = OkHttpClient.Builder()
            .cache(cache)

        if (BuildConfig.DEBUG) {
            c.addInterceptor(httpLoggingInterceptor)
                .addNetworkInterceptor(StethoInterceptor())
        }

        return c.build()
    }

    private fun provideSampleApiService(client: OkHttpClient): SampleApiService {
        return Retrofit.Builder()
            .client(client)
            .baseUrl("http://192.168.6.52")
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(createGson()))
            .build()
            .create(SampleApiService::class.java)
    }

    private fun createGson(): Gson {
        return GsonBuilder().setDateFormat("yyyy/MM/dd HH:mm:ss").create()
    }
}