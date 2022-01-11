package com.aberthier.myaccountingtable.dto

class ErrorResponseTestDto(
    val message: String,
    var stackTrace: String? = null,
    val code: Int,
    val status: String
)