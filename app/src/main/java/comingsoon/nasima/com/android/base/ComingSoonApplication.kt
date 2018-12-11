package comingsoon.nasima.com.android.base

import android.app.Application
import comingsoon.nasima.com.android.services.repository.ComingSoonRepository
import comingsoon.nasima.com.android.services.repository.RemoteApiEndPoint
import com.readystatesoftware.chuck.ChuckInterceptor
import comingsoon.nasima.com.android.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Nasima Hakkim on 20/11/18.
 */

class ComingSoonApplication : Application() {


    override fun onCreate() {
        super.onCreate()
        setRetrofit()
    }

    private fun setRetrofit() {

        val okHttpClient = OkHttpClient.Builder().addInterceptor(ChuckInterceptor(baseContext)).build()

        retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .baseUrl(BuildConfig.BASE_URL)
            .build()

        remoteApiEndPoint = retrofit.create(RemoteApiEndPoint::class.java)

        comingSoonRepository = ComingSoonRepository(getRetrofit())
    }


    companion object {

        private lateinit var retrofit: Retrofit

        private lateinit var remoteApiEndPoint: RemoteApiEndPoint

        private lateinit var comingSoonRepository: ComingSoonRepository

        fun getRetrofit(): RemoteApiEndPoint = remoteApiEndPoint

        fun getComingSoonRepository(): ComingSoonRepository = comingSoonRepository

    }
}