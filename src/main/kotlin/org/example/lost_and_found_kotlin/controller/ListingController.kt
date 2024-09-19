package org.example.lost_and_found_kotlin.controller

import org.example.lost_and_found_kotlin.domain.listing.ListingDTO
import org.example.lost_and_found_kotlin.service.ListingService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/listings")
class ListingController (
    private val listingService: ListingService
) {
    val log: Logger = LoggerFactory.getLogger(ListingController::class.java)

    @PreAuthorize("isAuthenticated()")
    @GetMapping("{listingId}")
    fun retrieveListing(@PathVariable("listingId") id: Long) : ListingDTO {
        log.info("retrieving listing by id: $id")
        listingService.findByListingId(id)
            .let {
                log.info("listing found: $it")
                return it
            }
    }

    @GetMapping("/get-all-listings")
    fun retrieveAllListings() : List<ListingDTO> {
        return listingService.getAllListings()
    }

    @PostMapping("/create")
    fun createListing(@RequestBody listingDTO: ListingDTO) : ListingDTO {
        return listingService.saveListing(listingDTO)
    }

    @DeleteMapping("/delete/{listingId}")
    fun deleteListing(@PathVariable("listingId") id: Long) {
        listingService.deleteListing(id)
    }
}