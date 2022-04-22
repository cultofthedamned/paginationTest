package com.klinovvlad.paginationtest.view.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.klinovvlad.paginationtest.R
import com.klinovvlad.paginationtest.databinding.ActivityMainBinding
import com.klinovvlad.paginationtest.network.api.UserApi
import com.klinovvlad.paginationtest.network.instance.UserInstance
import com.klinovvlad.paginationtest.view.adapters.UserAdapter
import com.klinovvlad.paginationtest.viewmodel.MainActivityViewModel
import com.klinovvlad.paginationtest.viewmodel.MainActivityViewModelFactory
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainActivityViewModel by lazy {
        ViewModelProvider(
            viewModelStore,
            MainActivityViewModelFactory(UserInstance.userApi)
        ).get(MainActivityViewModel::class.java)
    }
    private val userAdapter: UserAdapter by lazy {
        UserAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.userRecycMain.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
            adapter = userAdapter
        }
        lifecycleScope.launch {
            viewModel.users.collectLatest { pagedData ->
                userAdapter.submitData(pagedData)
            }
        }
    }

}