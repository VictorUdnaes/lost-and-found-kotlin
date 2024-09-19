package org.example.lost_and_found_kotlin.domain.listing

import jakarta.persistence.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Listing (
    @Id
    @Indexed(unique = true)
    val listingID: Long,
    @Indexed(unique = true)
    val title: String,
    val description: String
) {
    fun toDTO() : ListingDTO {
        return ListingDTO(
            listingID,
            title,
            description
        )
    }

    override fun toString() = "Listing(" +
            "listingID = $listingID, " +
            "title = $title, " +
            "description = $description" +
            ")"

    companion object {
        fun valueOf(listingDTO: ListingDTO) : Listing {
            return Listing(
                listingDTO.listingID,
                listingDTO.title,
                listingDTO.description
            )
        }
    }
}