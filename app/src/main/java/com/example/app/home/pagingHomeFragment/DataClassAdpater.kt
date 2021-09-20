package com.example.app.home.pagingHomeFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.app.databinding.ItemBinding
import com.example.app.model.Results

class DataClassAdpater(diffCallback: DiffUtil.ItemCallback<Results>, private var onClickItem :(Results)->Unit) :
    PagingDataAdapter<Results, DataClassAdpater.ViewHolder>(diffCallback) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = ItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position).let{ redditPost ->
            if (redditPost != null) {
                holder.bind(redditPost)
            }
        }
    }
   inner class ViewHolder (private val itemBinding: ItemBinding): RecyclerView.ViewHolder(itemBinding.root){
        fun bind(results: Results){
            itemBinding.result = results
            itemBinding.root.setOnClickListener {
                onClickItem(results)
            }
        }
    }
    }


object UserComparator : DiffUtil.ItemCallback<Results>() {
    override fun areItemsTheSame(oldItem: Results, newItem: Results): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Results, newItem: Results): Boolean {
        return oldItem == newItem
    }
}