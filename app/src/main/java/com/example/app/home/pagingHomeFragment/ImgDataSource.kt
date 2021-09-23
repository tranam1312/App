package com.example.app.home.pagingHomeFragment


import androidx.paging.PagingState
import androidx.paging.rxjava2.RxPagingSource
import com.example.app.Api.SeverApi
import com.example.app.model.Results
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.Schedulers.io


class ImgDataSource(private val severApi: SeverApi): RxPagingSource<Int, Results>() {
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


    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, Results>> {
        val nextPage = params.key ?: 1
        return  severApi.getAll(nextPage).subscribeOn(Schedulers.io())
            .map{it -> LoadResult.Page(it.results,if (nextPage == 1) null else nextPage - 1,
                nextPage + 1) as LoadResult<Int,Results> }
            .onErrorReturn{LoadResult.Error(it)}
    }
}


