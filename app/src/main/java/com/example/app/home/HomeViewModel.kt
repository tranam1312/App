package com.example.app.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import androidx.paging.rxjava2.cachedIn
import com.example.app.Repositrory
import com.example.app.model.Results
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class HomeViewModel : ViewModel() {
    private val repositrory = Repositrory()
    private  val compositeDisposable: CompositeDisposable = CompositeDisposable()
    private val  getData  = MutableLiveData<PagingData<Results>>()
    val liveData :LiveData<PagingData<Results>>
    get()= getData
    init {
        compositeDisposable.add(
            repositrory.getData().cachedIn(viewModelScope).subscribe{
                getData.value = it
            }
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}
