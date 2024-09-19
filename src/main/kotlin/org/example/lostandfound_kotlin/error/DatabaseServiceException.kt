package org.example.lostandfound_kotlin.error

class DatabaseServiceException : RuntimeException {
    constructor(message: String) : super(message)
    constructor(message: String, cause: Throwable) : super(message, cause)
}