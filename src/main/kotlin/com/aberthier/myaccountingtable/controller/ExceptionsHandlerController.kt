package com.aberthier.myaccountingtable.controller

import com.aberthier.myaccountingtable.exceptions.ConflictErrorException
import com.aberthier.myaccountingtable.exceptions.ErrorResponse
import com.aberthier.myaccountingtable.exceptions.NotFondException
import org.hibernate.exception.ConstraintViolationException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.dao.DataIntegrityViolationException
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

@ControllerAdvice
class ExceptionsHandlerController(
    val logger: Logger = LoggerFactory.getLogger(ExceptionsHandlerController::class.java)
) {
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
        ConflictErrorException::class
    )
    fun conflict(e: Exception): ResponseEntity<ErrorResponse> {
        return generateErrorResponse(HttpStatus.CONFLICT, "Conflict on creation resource", e)
    }

    @ExceptionHandler(
        DataIntegrityViolationException::class
    )
    fun dataIntegrityViolation(e: Exception): ResponseEntity<ErrorResponse> {
        return generateErrorResponse(HttpStatus.CONFLICT, "SQL constraint Exception", e)
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

        val sw = StringWriter()
        val pw = PrintWriter(sw)
        e.printStackTrace(pw)
        val stackTrace = sw.toString()

        when (System.getenv("ENV")?.uppercase(Locale.getDefault())) {
            "STAGING" -> logger.debug(stackTrace)
            "PRODUCTION" -> null
            else -> logger.debug(stackTrace)
        }
        
        val stackTraceMessage =
            when (System.getenv("ENV")?.uppercase(Locale.getDefault())) {
                "STAGING" -> stackTrace // returning the stack trace
                "PRODUCTION" -> null // returning no stack trace
                else -> stackTrace // default behavior
            }

        return ResponseEntity(ErrorResponse(httpStatus, message, stackTraceMessage), httpStatus)
    }
}