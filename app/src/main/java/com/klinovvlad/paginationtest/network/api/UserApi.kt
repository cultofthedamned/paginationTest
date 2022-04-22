package com.klinovvlad.paginationtest.network.api

import com.klinovvlad.paginationtest.model.UserNetworkData
import com.klinovvlad.paginationtest.model.UserNetworkData.UserResults
import com.klinovvlad.paginationtest.utils.GET_MAIN_API
import com.klinovvlad.paginationtest.utils.NETWORK_PAGE_SIZE
import com.klinovvlad.paginationtest.utils.USERS_STARTING_PAGE_INDEX
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApi {

    @GET(GET_MAIN_API)
    fun getData(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int = NETWORK_PAGE_SIZE
    ): Call<UserNetworkData>

}