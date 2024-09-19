package org.example.lost_and_found_kotlin.domain.listing

import org.jetbrains.annotations.NotNull

data class ListingPreviewDTO (
    @get:NotNull
    val listingID: Long,
    var title: String,
) {
    companion object {
        fun mapToPreviewDTO(listing: Listing) : ListingPreviewDTO {
            return ListingPreviewDTO(
                listing.listingID,
                listing.title
            )
        }
    }
}