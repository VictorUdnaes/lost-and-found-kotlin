package org.example.lostandfound_kotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@SpringBootApplication(scanBasePackages = ["org.example.lostandfound_kotlin"])
class LostAndFoundKotlinApplication

fun main(args: Array<String>) {
    runApplication<LostAndFoundKotlinApplication>(*args)
}
