package com.klinovvlad.paginationtest.network.instance

import com.klinovvlad.paginationtest.network.api.UserApi
import com.klinovvlad.paginationtest.utils.MAIN_BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object UserInstance {

    val userApi: UserApi by lazy {
        Retrofit.Builder()
            .baseUrl(MAIN_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserApi::class.java)
    }

}