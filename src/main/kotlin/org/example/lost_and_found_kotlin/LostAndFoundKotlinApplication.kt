package org.example.lost_and_found_kotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["org.example.lost_and_found_kotlin"])
class LostAndFoundKotlinApplication

fun main(args: Array<String>) {
    runApplication<LostAndFoundKotlinApplication>(*args)
}
