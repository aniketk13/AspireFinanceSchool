package com.aspire.aspirefinanceschool.API

import com.aspire.aspirefinanceschool.model.AspireResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface AspireFinanceAPI {
    @GET("api/v1/get_student_details/")
    suspend fun getStudentDetails(@Query("next_page") page_num: Int): AspireResponse
}