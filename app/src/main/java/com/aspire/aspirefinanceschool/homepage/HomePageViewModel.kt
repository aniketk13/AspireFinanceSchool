package com.aspire.aspirefinanceschool.homepage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.aspire.aspirefinanceschool.paging.StudentNamesRepository

class HomePageViewModel : ViewModel() {
    val listOfStudents = StudentNamesRepository().getStudentNames().cachedIn(viewModelScope)

}