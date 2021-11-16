package com.example.appchat.base.module

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.ui.input.key.Key.Companion.T
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil

abstract class BasePagingAdapter<T : Any,VB: ViewDataBinding>(
    private val diffCallback: DiffCallback<T>
) : PagingDataAdapter<T,CustomViewHolder<VB,T>>(diffCallback) {
    override fun onBindViewHolder(holder: CustomViewHolder<VB,T>, position: Int) {
        getItem(position).let {

            if (it!= null) {
                bindView(holder.binding as VB, it,position )
            }
            holder.binding.executePendingBindings()
            }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder<VB,T> {
        return  CustomViewHolder<VB,T>(DataBindingUtil.inflate(LayoutInflater.from(
            parent.context), getLayoutViewBinding(viewType), parent, false)).apply { bindFirstTime(this) }
    }

     abstract fun getLayoutViewBinding (viewType: Int):Int

    protected open fun bindFirstTime(binding: CustomViewHolder<VB, T>) {}
    protected open fun bindView(binding: VB, item:T, position: Int) {}




}
abstract  class DiffCallback<T>: DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem:T): Boolean {
        return sameItemComposition(oldItem, newItem)
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return sameItemContent(oldItem, newItem)
    }
    abstract fun sameItemComposition (oldItem: T, newItem: T) : Boolean
    abstract  fun sameItemContent (oldItem: T, newItem: T): Boolean
}

