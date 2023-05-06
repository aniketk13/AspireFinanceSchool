package com.aspire.aspirefinanceschool.paging

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData

//repository handling the work between ViewModel and the Pager
class StudentNamesRepository {
    fun getStudentNames() = Pager(
        config = PagingConfig(pageSize = 30, maxSize = 140),
        pagingSourceFactory = { PagingSource() }
    ).liveData
}