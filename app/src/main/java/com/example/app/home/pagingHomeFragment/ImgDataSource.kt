package com.example.app.home.pagingHomeFragment

import android.content.ClipData
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.ItemKeyedDataSource
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.app.Api.SeverApi
import com.example.app.Api.SeverBuild
import com.example.app.model.Results
import com.example.app.model.User
import java.lang.Exception

class ImgDataSource(private val severApi: SeverApi): PagingSource<Int, Results>() {
    override fun getRefreshKey(state: PagingState<Int, Results>): Int? {
        // Try to find the page key of the closest page to anchorPosition, from
        // either the prevKey or the nextKey, but you need to handle nullability
        // here:
        //  * prevKey == null -> anchorPage is the first page.
        //  * nextKey == null -> anchorPage is the last page.
        //  * both prevKey and nextKey null -> anchorPage is the initial page, so
        //    just return null.
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Results> {
        try {
            val nextPage = params.key ?: 1
            val results = severApi.getAll(nextPage)
            return LoadResult.Page(
                results.results,
                if (nextPage == 1) null else nextPage - 1,
                nextPage + 1
            )
        }catch (e: Exception){
            return LoadResult.Error(e)
        }
    }
}


