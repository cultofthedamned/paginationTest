package com.klinovvlad.paginationtest.model.pagination.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.klinovvlad.paginationtest.databinding.ItemLoadingStateBinding
import com.klinovvlad.paginationtest.view.adapters.UserAdapter

class UserLoadingStateAdapter(
    private val adapter: UserAdapter
) : LoadStateAdapter<UserLoadingStateAdapter.UserPagingAdapterHolder>() {

    override fun onBindViewHolder(holder: UserPagingAdapterHolder, loadState: LoadState) {
        holder.bind(loadState) { adapter.retry() }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): UserPagingAdapterHolder {
        return UserPagingAdapterHolder(
            ItemLoadingStateBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            )
        ) { adapter.retry() }
    }

    class UserPagingAdapterHolder(
        private val binding: ItemLoadingStateBinding,
        private val retry: () -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(state: LoadState, retry: () -> Unit) {
            if (state is LoadState.Error) {
                binding.tryNetworkRequest.visibility = View.VISIBLE
                binding.tryNetworkRequest.setOnClickListener {
                    retry()
                }
            } else if (state is LoadState.Loading) {
                binding.progressbar.visibility = View.VISIBLE
            }
        }
    }


}