package org.example.lost_and_found_kotlin.domain.listing

import org.jetbrains.annotations.NotNull

data class ListingDTO (
    @get:NotNull
    val listingID: Long,
    var title: String,
    var description: String
)