package com.example.app.home.pagingHomeFragment


import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.paging.rxjava2.RxPagingSource
import com.example.app.Api.SeverApi
import com.example.app.model.Results
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.Schedulers.io


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
                // Start refresh at page 1 if undefined.
                val nextPageNumber = params.key ?: 1
                val response = severApi.getAll( nextPageNumber)
                return LoadResult.Page(
                    data = response.results,
                    prevKey = null, // Only paging forward.
                    nextKey = nextPageNumber+1
                )
            } catch (e: Exception) {
                return LoadResult.Error(e)
            }
        }

}


