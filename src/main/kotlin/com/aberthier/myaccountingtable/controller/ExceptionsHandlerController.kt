package com.aberthier.myaccountingtable.controller

import com.aberthier.myaccountingtable.exceptions.NotFondException
import com.aberthier.myaccountingtable.exceptions.ErrorResponse
import org.hibernate.exception.ConstraintViolationException
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.client.HttpClientErrorException
import java.io.PrintWriter
import java.io.StringWriter
import java.util.*
import javax.persistence.EntityNotFoundException
import javax.persistence.NoResultException
import kotlin.NoSuchElementException

@ControllerAdvice
class ExceptionsHandlerController {
    @ExceptionHandler(
            ConstraintViolationException::class,
            HttpClientErrorException.BadRequest::class,
            MethodArgumentNotValidException::class,
            MissingServletRequestParameterException::class,
            IllegalArgumentException::class
    )
    fun constraintViolationException(e: Exception): ResponseEntity<ErrorResponse> {
        return generateErrorResponse(HttpStatus.BAD_REQUEST, "Bad request", e)
    }

    @ExceptionHandler(
            EntityNotFoundException::class,
            NoSuchElementException::class,
            NoResultException::class,
            EmptyResultDataAccessException::class,
            IndexOutOfBoundsException::class,
            KotlinNullPointerException::class,
            NotFondException::class
    )
    fun notFoundException(e: Exception): ResponseEntity<ErrorResponse> {
        return generateErrorResponse(HttpStatus.NOT_FOUND, "Resource not found", e)
    }

    @ExceptionHandler(
            Exception::class
    )
    fun internalServerErrorException(e: Exception): ResponseEntity<ErrorResponse> {
        return generateErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Generic internal error", e)
    }

    private fun generateErrorResponse(
            httpStatus: HttpStatus,
            message: String,
            e: Exception
    ): ResponseEntity<ErrorResponse> {
        // converting the exception stack trace to a string
        val sw = StringWriter()
        val pw = PrintWriter(sw)
        e.printStackTrace(pw)
        val stackTrace = sw.toString()

        // example: logging the stack trace
        // log.debug(stackTrace)

        // environment-based logic
        val stackTraceMessage =
                when (System.getenv("ENV")?.uppercase(Locale.getDefault())) {
                    "STAGING" -> stackTrace // returning the stack trace
                    "PRODUCTION" -> null // returning no stack trace
                    else -> stackTrace // default behavior
                }

        return ResponseEntity(ErrorResponse(httpStatus, message, stackTraceMessage), httpStatus)
    }
}