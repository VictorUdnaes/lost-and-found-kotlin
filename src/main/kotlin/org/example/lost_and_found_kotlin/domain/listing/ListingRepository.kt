package org.example.lost_and_found_kotlin.domain.listing

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface ListingRepository : MongoRepository<Listing, Long> {
    fun findByTitle(title: String): Optional<Listing>
    fun findByDescription(description: String): Optional<Listing>
    fun findByListingID(listingID: Long): Optional<Listing>
    fun deleteByListingID(listingID: Long): Optional<Listing>
}