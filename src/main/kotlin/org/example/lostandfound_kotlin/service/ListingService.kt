package org.example.lostandfound_kotlin.service

import org.springframework.dao.DuplicateKeyException
import org.example.lostandfound_kotlin.domain.listing.Listing
import org.example.lostandfound_kotlin.domain.listing.ListingDTO
import org.example.lostandfound_kotlin.domain.listing.ListingRepository
import org.example.lostandfound_kotlin.error.DatabaseServiceException
import org.example.lostandfound_kotlin.utils.extractDuplicateAttribute
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.slf4j.Logger


@Service
class ListingService (
    private val listingRepository: ListingRepository
) {
    private val log: Logger = LoggerFactory.getLogger(ListingService::class.java)

    fun findByListingId(id : Long) : ListingDTO {
        log.info("finding listing by id: $id")
        return listingRepository.findByListingID(id).orElseThrow { DatabaseServiceException("listing not found") }
            .toDTO()
    }

    fun getAllListings() : List<ListingDTO> {
        log.info("getting all listings")
        return listingRepository.findAll()
            .map { it.toDTO() }
    }

    fun saveListing(listing: ListingDTO): ListingDTO {
        log.info("saving listing: $listing")
        listingRepository.findByListingID(listing.listingID).ifPresent { throw DatabaseServiceException("listing already exists") }
        try {
            listingRepository.save(Listing.valueOf(listing))
            return listing
        } catch (e: DuplicateKeyException) {
            throw DatabaseServiceException("listing with duplicate attribute: ${extractDuplicateAttribute(e)}") //TODO: FIXXXXXXXX
        }
    }

    fun deleteListing(id: Long): ListingDTO {
        log.info("deleting listing by id: $id")
        return listingRepository.deleteByListingID(id).orElseThrow { DatabaseServiceException("listing not found") }
            .toDTO()
    }

}