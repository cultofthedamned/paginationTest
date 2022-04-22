package com.klinovvlad.paginationtest.model.pagination.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.klinovvlad.paginationtest.model.UserNetworkData.UserResults
import com.klinovvlad.paginationtest.network.api.UserApi
import com.klinovvlad.paginationtest.utils.USERS_STARTING_PAGE_INDEX
import kotlinx.coroutines.delay
import retrofit2.*
import java.io.IOException

class UsersPagingSource(
    private val userApi: UserApi
) : PagingSource<Int, UserResults>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserResults> {
        return try {
            val page = params.key ?: USERS_STARTING_PAGE_INDEX
            val pageSize = params.loadSize
            val response = userApi.getData(page)
            val users = response.awaitResponse().body()?.results ?: emptyList()
            LoadResult.Page(
                users,
                if (page == pageSize) null else page - 1,
                if (users.isEmpty()) null else page + 1
            )
        } catch (e: IOException) {
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, UserResults>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.prevKey?.minus(1)
    }

}