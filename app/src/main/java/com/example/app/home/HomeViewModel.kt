package com.example.app.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.app.Api.SeverApi
import com.example.app.Api.SeverBuild
import com.example.app.home.pagingHomeFragment.ImgDataSource
import com.example.app.model.Results
import com.example.app.model.User
import kotlinx.coroutines.flow.Flow


class HomeViewModel : ViewModel() {

        val severApi = SeverBuild.instance().create(SeverApi::class.java)

    val results: Flow<PagingData<Results>> = Pager(PagingConfig(pageSize = 20)) {
        ImgDataSource(severApi)
    }.flow
        .cachedIn(viewModelScope)


}
