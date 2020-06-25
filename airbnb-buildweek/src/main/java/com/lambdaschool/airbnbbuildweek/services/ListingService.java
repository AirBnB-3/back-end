package com.lambdaschool.airbnbbuildweek.services;

import com.lambdaschool.airbnbbuildweek.models.Listing;

import java.util.List;

public interface ListingService
{
    List<Listing> findAll();

    Listing findListingById(long id);

    void delete(long id);

    Listing update(
        Listing updateListing,
        long listingid);

    Listing save(long userid, Listing listing);

    List<Listing> findByUserName(String username);
}
