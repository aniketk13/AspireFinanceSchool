package com.aspire.aspirefinanceschool.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.aspire.aspirefinanceschool.API.RetrofitInstance
import com.aspire.aspirefinanceschool.model.StudentNames

class PagingSource() : PagingSource<Int, StudentNames>() {

    //loading data function
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, StudentNames> {
        return try {
            val position = params.key ?: 0
            val response = RetrofitInstance.api.getStudentDetails(position)

            return LoadResult.Page(
                data = response.data.names,
                prevKey = if (position == 0) null else position - 1,
                nextKey = if (position == 49) null else position + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    //getting the refresh key if the source has lost track of positions
    override fun getRefreshKey(state: PagingState<Int, StudentNames>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

}