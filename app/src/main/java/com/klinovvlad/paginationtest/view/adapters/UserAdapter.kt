package com.klinovvlad.paginationtest.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.klinovvlad.paginationtest.databinding.UserItemBinding
import com.klinovvlad.paginationtest.model.UserNetworkData.UserResults

class UserAdapter : PagingDataAdapter<UserResults, UserAdapter.UserViewHolder>(MainUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.UserViewHolder {
        return UserViewHolder(
            UserItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            )
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val item = getItem(position)
        item.let { holder.bind(it!!) }
    }

    class UserViewHolder(
        private val binding: UserItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: UserResults) {
            binding.textItemName.text = data.name.first
        }
    }

    class MainUtil : DiffUtil.ItemCallback<UserResults>() {
        override fun areItemsTheSame(oldItem: UserResults, newItem: UserResults): Boolean =
            oldItem.login.uuid == newItem.login.uuid

        override fun areContentsTheSame(oldItem: UserResults, newItem: UserResults): Boolean =
            oldItem == newItem
    }

}