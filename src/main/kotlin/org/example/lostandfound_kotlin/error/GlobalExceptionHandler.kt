package org.example.lostandfound_kotlin.error

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest


@ControllerAdvice
class GlobalExceptionHandler {
    val log: Logger = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)

    @ExceptionHandler(DatabaseServiceException::class)
    fun handleDatabaseServiceException(e: DatabaseServiceException, request: WebRequest): ResponseEntity<String> {
        log.error("Database service exception: ${e.message}")
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body("An error occurred: ${e.message} \n" + "request: $request")
    }

    @ExceptionHandler(Exception::class)
    fun handleGenericException(e: Exception, request: WebRequest): ResponseEntity<String> {
        log.error("An error occurred: ${e}")
        return ResponseEntity
            .status(500)
            .body("An error occurred: ${e.message}")
    }
}