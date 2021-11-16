package com.example.app.home.pagingHomeFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.app.R
import com.example.app.databinding.ItemBinding
import com.example.app.model.Results
import com.example.appchat.base.module.BasePagingAdapter
import com.example.appchat.base.module.DiffCallback

class DataClassAdpater(private var onClickItem :(Results)->Unit) :
 BasePagingAdapter<Results,ItemBinding>(UserComparator)    {


    override fun getLayoutViewBinding(viewType: Int): Int = R.layout.item

    override fun bindView(binding: ItemBinding, item: Results, position: Int) {
        super.bindView(binding, item, position)
                binding.result = item
                binding.root.setOnClickListener {
                    onClickItem(item)
                }
    }

}


object UserComparator : DiffCallback<Results>() {

    override fun sameItemComposition(oldItem: Results, newItem: Results): Boolean {
        return oldItem.id == newItem.id
    }

    override fun sameItemContent(oldItem: Results, newItem: Results): Boolean {
        return oldItem == newItem
    }
}
