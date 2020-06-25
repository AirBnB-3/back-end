package com.lambdaschool.airbnbbuildweek.controllers;

import com.lambdaschool.airbnbbuildweek.models.ErrorDetail;
import com.lambdaschool.airbnbbuildweek.models.Listing;
import com.lambdaschool.airbnbbuildweek.models.User;
import com.lambdaschool.airbnbbuildweek.services.ListingService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/listings")
public class ListingController
{
    @Autowired
    ListingService listingService;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(value = "/listings",
        produces = {"application/json"})
    public ResponseEntity<?> listAllListings()
    {
        List<Listing> allListings = listingService.findAll();
        return new ResponseEntity<>(allListings,
            HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(value = "/listing/{listingId}",
        produces = {"application/json"})
    public ResponseEntity<?> getListingById(
        @PathVariable
            Long listingId)
    {
        Listing l = listingService.findListingById(listingId);
        return new ResponseEntity<>(l,
            HttpStatus.OK);
    }

    @DeleteMapping(value = "/listing/{listingId}")
    public ResponseEntity<?> deleteListingById(
        @PathVariable
            long listingId)
    {
        listingService.delete(listingId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @PutMapping(value = "/listing/{listingid}",
//        consumes = {"application/json"})
//    public ResponseEntity<?> updateListing(
//        @PathVariable
//            long listingid,
//        @Valid
//        @RequestBody
//            Listing updateListing)
//    {
//        updateListing.setListingid(listingid);
//        listingService.save(userrepos, updateListing);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

    @ApiOperation(value = "updates a listing with the information given in the request body",
        response = Void.class)
    @ApiResponses(value = {@ApiResponse(code = 200,
        message = "Listing Found",
        response = User.class), @ApiResponse(code = 404,
        message = "Listing Not Found",
        response = ErrorDetail.class)})
    @PatchMapping(value = "/listing/{listingid}",
        consumes = {"application/json"})
    @PreAuthorize("hasAnyRole('USER')") // this may break
    public ResponseEntity<?> updateListing(
        @ApiParam(value = "a listing object with just the information needed to be updated",
            required = true)
        @RequestBody
            Listing updateListing,
        @ApiParam(value = "listingid",
            required = true,
            example = "4")
        @PathVariable
            long listingid)
    {
        listingService.update(updateListing,
            listingid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/user/{userid}",
        consumes = {"application/json"})
    public ResponseEntity<?> addNewListing(
        @PathVariable
            long userid,
        @Valid
        @RequestBody
            Listing newListing)
    {
        newListing.setListingid(0);
        newListing = listingService.save(userid, newListing);

        HttpHeaders responseHeaders = new HttpHeaders();
        // http://localhost:2019/orders/order/newordnum
        URI newOrderURI = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{listingid}")
            .buildAndExpand(newListing.getListingid())
            .toUri();
        responseHeaders.setLocation(newOrderURI);

        return new ResponseEntity<>(null,
            responseHeaders,
            HttpStatus.CREATED);
    }

    @GetMapping(value = "/{userName}",
        produces = {"application/json"})
    public ResponseEntity<?> findListingByUserName(
        @PathVariable
            String userName)
    {
        List<Listing> theListings = listingService.findByUserName(userName);
        return new ResponseEntity<>(theListings,
            HttpStatus.OK);
    }
}
