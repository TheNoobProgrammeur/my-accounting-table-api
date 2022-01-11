package com.aberthier.myaccountingtable.exceptions

import com.fasterxml.jackson.annotation.JsonFormat
import lombok.Data
import org.springframework.http.HttpStatus
import java.util.*

class ErrorResponse (
        httpStatus: HttpStatus,
        val message: String,
        var stackTrace: String? = null
) {

    val code: Int
    val status: String

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy hh:mm:ss")
    val timestamp: Date = Date()

    init {
        code = httpStatus.value()
        status = httpStatus.name
    }
}