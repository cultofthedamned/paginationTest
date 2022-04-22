package com.klinovvlad.paginationtest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.klinovvlad.paginationtest.model.pagination.source.UsersPagingSource
import com.klinovvlad.paginationtest.network.api.UserApi
import com.klinovvlad.paginationtest.utils.NETWORK_PAGE_SIZE

class MainActivityViewModel(private val userApi: UserApi) : ViewModel() {

    val users = Pager(
        config = PagingConfig(
            enablePlaceholders = true,
            initialLoadSize = NETWORK_PAGE_SIZE,
            pageSize = NETWORK_PAGE_SIZE,
            prefetchDistance = 10,
        ),
        pagingSourceFactory = {
            UsersPagingSource(userApi = userApi)
        }).flow.cachedIn(viewModelScope)

}