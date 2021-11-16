package com.example.app




import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava2.flowable
import androidx.paging.rxjava2.observable
import com.example.app.Api.SeverApi
import com.example.app.Api.SeverBuild
import com.example.app.home.pagingHomeFragment.ImgDataSource
import com.example.app.model.Results
import io.reactivex.Flowable
import io.reactivex.Observable


class Repositrory  {

    private val severApi = SeverBuild.instance()
    private val imgDataSource  = ImgDataSource(severApi)



}