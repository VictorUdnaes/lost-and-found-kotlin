package org.example.lostandfound_kotlin.domain.listing

import org.jetbrains.annotations.NotNull

data class ListingDTO (
    @get:NotNull
    val listingID: Long,
    var title: String,
    var description: String
)