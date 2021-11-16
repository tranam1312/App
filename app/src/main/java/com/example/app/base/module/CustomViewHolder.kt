package com.example.appchat.base.module

import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

  class CustomViewHolder<V: ViewDataBinding, T:Any>(private val viewBinding: ViewDataBinding): RecyclerView.ViewHolder(viewBinding.root) {
    var binding : ViewDataBinding = viewBinding
   open fun bind(T:Any){

    }
}