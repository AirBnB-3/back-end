package com.lambdaschool.airbnbbuildweek.services;

import com.lambdaschool.airbnbbuildweek.exceptions.ResourceNotFoundException;
import com.lambdaschool.airbnbbuildweek.handlers.HelperFunctions;
import com.lambdaschool.airbnbbuildweek.models.Listing;
import com.lambdaschool.airbnbbuildweek.models.User;
import com.lambdaschool.airbnbbuildweek.repository.ListingRepository;
import com.lambdaschool.airbnbbuildweek.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "listingService")
public class ListingServiceImpl implements ListingService
{
    @Autowired
    private ListingRepository listingrepos;

    @Autowired
    private ListingService listingService;

    @Autowired
    private UserService userService;

    @Autowired
    private HelperFunctions helper;

    @Autowired
    private UserRepository userrepos;

    @Override
    public List<Listing> findAll()
    {
        List<Listing> list = new ArrayList<>();

        listingrepos.findAll()
            .iterator()
            .forEachRemaining(list::add);
        return list;
    }

    @Override
    public Listing findListingById(long id)
    {
        return listingrepos.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Listing with id " + id + " Not Found!"));
    }

    @Transactional
    @Override
    public void delete(long id)
    {
        if (listingrepos.findById(id)
            .isPresent())
        {
            if (helper.isAuthorizedToMakeChange(listingrepos.findById(id)
                .get()
                .getUser()
                .getUsername()))
            {
                listingrepos.deleteById(id);
            }
        } else
        {
            throw new ResourceNotFoundException("Listing with id " + id + " Not Found!");
        }
    }

    @Override
    public List<Listing> findByUserName(String username)
    {
        return listingrepos.findAllByUser_Username(username.toLowerCase());
    }

    //    @Transactional
    //    @Override
    //    public Listing update(
    //        long listingid,
    //        String listingname,
    //        String location,
    //        int minnumnights,
    //        int maxnumguests,
    //        boolean petsallowed,
    //        int numrooms,
    //        int numbeds,
    //        double optimalPrice,
    //        int size)
    //    {
    //        if (listingrepos.findById(listingid)
    //            .isPresent())
    //        {
    //            if (helper.isAuthorizedToMakeChange(listingrepos.findById(listingid)
    //                .get()
    //                .getUser()
    //                .getUsername()))
    //            {
    //                Listing listing = findListingById(listingid);
    //                listing.setListingname(listingname);
    //                listing.setMinnumnights(minnumnights);
    //                listing.setMaxnumguests(maxnumguests);
    //                listing.setPetsallowed(petsallowed);
    //                listing.setNumrooms(numrooms);
    //                listing.setNumbeds(numbeds);
    //                listing.setOptimalPrice(optimalPrice);
    //                listing.setSize(size);
    //
    //                return listingrepos.save(listing);
    //            } else
    //            {
    //                // note we should never get to this line but is needed for the compiler
    //                // to recognize that this exception can be thrown
    //                throw new ResourceNotFoundException("This user is not authorized to make change");
    //            }
    //        } else
    //        {
    //            throw new ResourceNotFoundException("Listing with id " + listingid + " Not Found!");
    //        }
    //    }

    //    @Transactional
    //    @Override
    //    public Listing update(
    //        Listing updateListing,
    //        long listingid)
    //    {
    //        if (listingrepos.findById(listingid)
    //            .isPresent())
    //        {
    //            if (helper.isAuthorizedToMakeChange(listingrepos.findById(listingid)
    //                .get()
    //                .getUser()
    //                .getUsername()))
    //            {
    //
    //                Listing listing = findListingById(listingid);
    //
    //                listing.setUser(updateListing.getUser());
    //                listing.setListingname(updateListing.getListingname());
    //                listing.setRoomtype(updateListing.getRoomtype());
    //                listing.setZipcode(updateListing.getZipcode());
    //                listing.setNeighborhoodgroup(updateListing.getNeighborhoodgroup());
    //                listing.setNeighborhood(updateListing.getNeighborhood());
    //                listing.setMinnumnights(updateListing.getMinnumnights());
    //                listing.setOptimalPrice(updateListing.getOptimalPrice());
    //                return listingrepos.save(listing);
    //            } else
    //            {
    //                // note we should never get to this line but is needed for the compiler
    //                // to recognize that this exception can be thrown
    //                throw new ResourceNotFoundException("This user is not authorized to make change");
    //            }
    //        } else
    //        {
    //            throw new ResourceNotFoundException("Listing with id " + listingid + " Not Found!");
    //        }
    //    }

    @Transactional
    @Override
    public Listing update(
        Listing updateListing,
        long listingid)
    {
        //        User currentUser = userService.getCurrentUserInfo(id);
        Listing currentListing = listingService.findListingById(listingid);

        //        if (helper.isAuthorizedToMakeChange(currentUser.getUsername()))
        //        {

        if (updateListing.getRoomtype() != null)
        {
            currentListing.setRoomtype(updateListing.getRoomtype());
        }

        if (updateListing.getNeighbourhood() != null)
        {
            currentListing.setNeighbourhood(updateListing.getNeighbourhood());
        }

        if (updateListing.getMinnumnights() != 0)
        {
            currentListing.setMinnumnights(updateListing.getMinnumnights());
        }

        if (updateListing.getOptimalPrice() != 0)
        {
            currentListing.setOptimalPrice(updateListing.getOptimalPrice());
        }

        return listingrepos.save(currentListing);
        //        } else
        //        {
        //            {
        //                // note we should never get to this line but is needed for the compiler
        //                // to recognize that this exception can be thrown
        //                throw new ResourceNotFoundException("This user is not authorized to make change");
        //            }
        //        }
    }

    //    @Transactional
    //    @Override
    //    public Listing save(
    //        long userid,
    //        String listingname,
    //        String roomtype,
    //        String location,
    //        int minnumnights,
    //        int maxnumguests,
    //        boolean petsallowed,
    //        int numrooms,
    //        int numbeds,
    //        double optimalPrice,
    //        int size)
    //    {
    //        User currentUser = userService.findUserById(userid);
    //
    //        if (helper.isAuthorizedToMakeChange(currentUser.getUsername()))
    //        {
    //            Listing newListing = new Listing(
    //                currentUser,
    //                listingname,
    //                roomtype,
    //                location,
    //                minnumnights,
    //                maxnumguests,
    //                petsallowed,
    //                numrooms,
    //                numbeds,
    //                optimalPrice,
    //                size);
    //
    //            return listingrepos.save(newListing);
    //        } else
    //        {
    //            // note we should never get to this line but is needed for the compiler
    //            // to recognize that this exception can be thrown
    //            throw new ResourceNotFoundException("This user is not authorized to make change");
    //        }

    @Transactional
    @Override
    public Listing save(long userid, Listing listing)
    {
        User currentUser = userService.findUserById(userid);

        if (helper.isAuthorizedToMakeChange(currentUser.getUsername()))
        {
            Listing newListing = new Listing();

            if (listing.getListingid() != 0)
            {
                // put
                listingrepos.findById(listing.getListingid())
                    .orElseThrow(() -> new EntityNotFoundException("Listing " + listing.getListingid() + " Not Found"));

                newListing.setListingid(listing.getListingid());
            }

            newListing.setUser(userrepos.findById(userid)
                .orElseThrow(() -> new EntityNotFoundException("User " + listing.getUser()
                    .getUserid() + " Not Found")));
            newListing.setRoomtype(listing.getRoomtype());
            newListing.setNeighbourhood(listing.getNeighbourhood());
            newListing.setMinnumnights(listing.getMinnumnights());
            newListing.setOptimalPrice(listing.getOptimalPrice());

            return listingrepos.save(newListing);
        } else
        {
            // note we should never get to this line but is needed for the compiler
            // to recognize that this exception can be thrown
            throw new ResourceNotFoundException("This user is not authorized to make change");
        }
    }
}
