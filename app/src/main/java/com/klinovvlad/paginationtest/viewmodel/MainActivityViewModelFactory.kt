package com.klinovvlad.paginationtest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.klinovvlad.paginationtest.network.api.UserApi

class MainActivityViewModelFactory(private val userApi: UserApi) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
            return MainActivityViewModel(userApi) as T
        }
        throw IllegalArgumentException("UnknownViewModel")
    }

}