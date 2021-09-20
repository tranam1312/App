package com.example.app.home.detailFragment

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter

class LoadSateAdapter(private val retry :()->Unit): LoadStateAdapter<LoadStaeViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ) = LoadStaeViewHolder(parent, retry)

    override fun onBindViewHolder(
        holder:LoadStaeViewHolder,
        loadState: LoadState
    ) = holder.bind(loadState)
}