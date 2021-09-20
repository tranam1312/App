package com.example.app.bindingAdpter

import android.widget.ImageView
import androidx.constraintlayout.utils.widget.ImageFilterView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.app.R

class BindingAdapter {
    companion object{
        @JvmStatic
        @BindingAdapter("app:url")
        fun setImageUrl(view: ImageFilterView, url: String) {
            Glide.with(view).load(url).placeholder(R.drawable.ic_baseline_home_24)
                .into(view)
        }
    }
}