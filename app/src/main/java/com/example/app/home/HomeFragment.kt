package com.example.app.home

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.example.app.R
import com.example.app.databinding.HomeFragmentBinding
import com.example.app.home.detailFragment.DetailFragment
import com.example.app.home.detailFragment.LoadSateAdapter
import com.example.app.home.detailFragment.LoadStaeViewHolder
import com.example.app.home.pagingHomeFragment.DataClassAdpater
import com.example.app.home.pagingHomeFragment.UserComparator
import com.example.app.model.Results
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var recyclerView: RecyclerView
    private lateinit var dataClassAdpater: DataClassAdpater
    private  val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this)[HomeViewModel::class.java]
    }
    private lateinit var binding: HomeFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.home_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recyclerView = binding.recyclView
        dataClassAdpater = DataClassAdpater( onClickItem)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = dataClassAdpater
        lifecycleScope.launch {
        viewModel.flow.collectLatest {
            dataClassAdpater.submitData(it)
        }
        }

        binding.recyclView.adapter = dataClassAdpater.withLoadStateHeaderAndFooter(
            header = LoadSateAdapter { dataClassAdpater.retry() },
            footer = LoadSateAdapter { dataClassAdpater.retry() })

        dataClassAdpater.addLoadStateListener { loadState ->
            binding.recyclView.isVisible = loadState.source.refresh is LoadState.NotLoading
            binding.progressBar.isVisible = loadState.source.refresh is LoadState.Loading
            binding.retryButton.isVisible = loadState.source.refresh is LoadState.Error
        }
        binding.refresh.setOnRefreshListener {
            dataClassAdpater.retry()
            binding.refresh.isRefreshing = false
        }
        binding.retryButton.setOnClickListener {
            dataClassAdpater.retry()
        }
    }
    private val onClickItem: (Results) -> Unit = {
        val bundle = Bundle()
        bundle.putSerializable("data", it)
        requireView().findNavController().navigate(R.id.detailFragment, bundle)
        }
    }



