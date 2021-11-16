package com.example.app.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import androidx.paging.rxjava2.cachedIn
import com.example.app.Api.SeverBuild
import com.example.app.Repositrory
import com.example.app.home.pagingHomeFragment.ImgDataSource
import com.example.app.model.Results
import com.example.appchat.base.module.PagingSourceViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class HomeViewModel : ViewModel (){
    private val repositrory = Repositrory()
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    private val getData = MutableLiveData<PagingData<Results>>()
    val liveData: LiveData<PagingData<Results>>
        get() = getData
    private val severApi = SeverBuild.instance()


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    val flow = Pager(
        PagingConfig(pageSize = 20)
    ) {
        ImgDataSource(severApi)
    }.flow
        .cachedIn(viewModelScope)
}
