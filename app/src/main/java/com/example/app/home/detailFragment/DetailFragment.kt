package com.example.app.home.detailFragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.findNavController
import com.example.app.R
import com.example.app.databinding.DetailFragmentBinding
import com.example.app.model.Results

class DetailFragment : Fragment() {

    companion object {
        fun newInstance() = DetailFragment()
        const val FRAGMENT = "DETAIL_FRAGMENT"
    }
    private lateinit var binding: DetailFragmentBinding
    private lateinit var viewModel: DetailViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =DataBindingUtil.inflate(inflater, R.layout.detail_fragment, container,false)
        return binding.root
    }

    override fun onOptionsItemSelected(item: MenuItem)= when(item.itemId) {
        android.R.id.home->{
              view?.findNavController()?.popBackStack()
            true
        }
        else-> super.onOptionsItemSelected(item)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        binding.lifecycleOwner = this
        binding .viewodel=  viewModel
        Log.d("hehe","${arguments?.getSerializable("data") as Results}")
        viewModel.setResult( arguments?.getSerializable("data") as Results)
    }
}