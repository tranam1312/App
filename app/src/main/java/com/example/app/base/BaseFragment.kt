package com.example.appchat.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagingDataAdapter
import androidx.viewbinding.ViewBinding
import com.example.appchat.base.module.BasePagingAdapter
import com.example.appchat.base.module.CustomViewHolder

abstract class BaseFragment<VB:ViewBinding , VM: ViewModel>(): Fragment() {
    protected  lateinit var binding : VB
    protected lateinit var viewModel: ViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,getViewFragment(),container, false)

        viewModel = ViewModelProvider(this).get(getViewModel())
        return binding.root
    }

    abstract fun getViewFragment(): Int
    abstract fun getViewModel(): Class<VM>

}