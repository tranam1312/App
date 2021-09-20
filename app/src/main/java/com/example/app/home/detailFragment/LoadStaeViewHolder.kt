package com.example.app.home.detailFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.example.app.R
import com.example.app.databinding.ActivityLoginBinding.bind
import com.example.app.databinding.ItemReposLoadStateFooterBinding

class LoadStaeViewHolder(parent: ViewGroup, retry: () -> Unit
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context). inflate(R.layout.item_repos_load_state_footer, parent, false)
) {
    private val binding = ItemReposLoadStateFooterBinding.bind(itemView)
    private val progressBar: ProgressBar = binding.progressBar
    private val errorMsg: TextView = binding.errMessage
    private val retry: Button = binding.retryButton
        .also {
            it.setOnClickListener { retry() }
        }

    fun bind(loadState: LoadState) {
        if (loadState is LoadState.Error) {
            errorMsg.text = loadState.error.localizedMessage
        }
        progressBar.isVisible = loadState is LoadState.Loading
        retry.isVisible = loadState is LoadState.Error
        errorMsg.isVisible = loadState is LoadState.Error
    }
}