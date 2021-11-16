package com.example.appchat.base.module

import androidx.paging.PagingSource
import androidx.paging.PagingState

abstract class BasePagingSource<T : Any>( val pagingSourceViewModel: PagingSourceViewModel<T>):PagingSource<Int, T>() {
    override fun getRefreshKey(state: PagingState<Int, T>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> {
        val dataSource = loadDataSource()
        return LoadResult.Page(dataSource, pevPage(), nextpage())
    }
    abstract suspend fun loadDataSource(): List<T>
    abstract fun pevPage (): Int?
    abstract fun nextpage (): Int?
}



