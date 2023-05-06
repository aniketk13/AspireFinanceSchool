package com.aspire.aspirefinanceschool.model

data class AspireResponse(
    val success: Boolean,
    val status_code: Int,
    val message: String,
    val data: AspireData,
    val next_page: Int,
    val execution_time: Double
)

data class AspireData(
    val names: List<StudentNames>
)

data class StudentNames(
    val name: String,
    val serialnumber: Int
)