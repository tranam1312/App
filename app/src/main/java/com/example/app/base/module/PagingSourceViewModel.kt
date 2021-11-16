package com.example.appchat.base.module

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

abstract class PagingSourceViewModel<T : Any> :ViewModel(){
    open val pageSize = 20
    private var dataSource: BasePagingSource<T>? = null
    open val nextPage : MutableLiveData<Int> = MutableLiveData()
    open val pevPage : MutableLiveData<Int> = MutableLiveData()
    fun itemList (): Flow<PagingData<T>> {
     return  Pager(config = PagingConfig(pageSize),pagingSourceFactory = {createDataSource()}).flow
    }

     open fun createDataSource(): BasePagingSource<T>{
        return object : BasePagingSource<T>(this){
            override suspend fun loadDataSource(): List<T> {
                return loadData()
            }

            override fun nextpage(): Int? {
                return nextpage()
            }

            override fun pevPage(): Int? {
                return pevPage()
            }
        }

}
    abstract suspend fun loadData(): List<T>
    abstract fun nextpage (): Int?
    abstract fun pevPage (): Int?


}
