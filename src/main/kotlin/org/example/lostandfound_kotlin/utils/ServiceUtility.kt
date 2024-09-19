package org.example.lostandfound_kotlin.utils

import org.springframework.dao.DuplicateKeyException

fun extractDuplicateAttribute(e: DuplicateKeyException): String {
    val message = e.cause?.message ?: return "unknown"
    val regex = Regex("index: (\\w+)_\\d+ dup key")
    return regex.find(message)?.groups?.get(1)?.value ?: "unknown"
}