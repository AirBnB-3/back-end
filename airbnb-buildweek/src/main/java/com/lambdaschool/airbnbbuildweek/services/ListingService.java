package com.lambdaschool.airbnbbuildweek.services;

import com.lambdaschool.airbnbbuildweek.models.Listing;
import com.lambdaschool.airbnbbuildweek.models.User;

import java.util.List;

public interface ListingService
{
    List<Listing> findAll();

    Listing findListingById(long id);

    void delete(long id);

    Listing update(
        long listingid,
        Listing updateListing);

    Listing save(Listing listing);

    List<Listing> findByUserName(String username);
}
