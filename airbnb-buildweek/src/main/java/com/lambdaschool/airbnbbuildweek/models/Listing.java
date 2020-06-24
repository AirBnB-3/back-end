package com.lambdaschool.airbnbbuildweek.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "listings")
public class Listing extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long listingid;

    @NotNull
    @Column(nullable = false)
    private String listingname;

    @NotNull
    @Column(nullable = false)
    private String roomtype;

    @NotNull
    @Column(nullable = false)
    private String zipcode;

    private String neighborhoodgroup;

    private String neighborhood;

    private int minnumnights;

    private double optimalPrice;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "userid",
        nullable = false)
    @JsonIgnoreProperties(value = "listings",
        allowSetters = true)
    private User user;

    public Listing()
    {
    }

    public Listing(
        @NotNull User user,
        @NotNull String listingname,
        @NotNull String roomtype,
        @NotNull String zipcode,
        String neighborhoodgroup,
        String neighborhood,
        int minnumnights,
        double optimalPrice)
    {
        this.user = user;
        this.listingname = listingname;
        this.roomtype = roomtype;
        this.zipcode = zipcode;
        this.neighborhoodgroup = neighborhoodgroup;
        this.neighborhood = neighborhood;
        this.minnumnights = minnumnights;
        this.optimalPrice = optimalPrice;
    }

    public long getListingid()
    {
        return listingid;
    }

    public void setListingid(long listingid)
    {
        this.listingid = listingid;
    }

    public String getListingname()
    {
        return listingname;
    }

    public void setListingname(String listingname)
    {
        this.listingname = listingname;
    }

    public String getRoomtype()
    {
        return roomtype;
    }

    public void setRoomtype(String roomtype)
    {
        this.roomtype = roomtype;
    }

    public String getZipcode()
    {
        return zipcode;
    }

    public void setZipcode(String zipcode)
    {
        this.zipcode = zipcode;
    }

    public String getNeighborhoodgroup()
    {
        return neighborhoodgroup;
    }

    public void setNeighborhoodgroup(String neighborhoodgroup)
    {
        this.neighborhoodgroup = neighborhoodgroup;
    }

    public String getNeighborhood()
    {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood)
    {
        this.neighborhood = neighborhood;
    }

    public int getMinnumnights()
    {
        return minnumnights;
    }

    public void setMinnumnights(int minnumnights)
    {
        this.minnumnights = minnumnights;
    }

    public double getOptimalPrice()
    {
        return optimalPrice;
    }

    public void setOptimalPrice(double optimalPrice)
    {
        this.optimalPrice = optimalPrice;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    @Override
    public String toString()
    {
        return "Listing{" +
            "listingid=" + listingid +
            ", listingname='" + listingname + '\'' +
            ", roomtype='" + roomtype + '\'' +
            ", zipcode='" + zipcode + '\'' +
            ", neighborhoodgroup='" + neighborhoodgroup + '\'' +
            ", neighborhood='" + neighborhood + '\'' +
            ", minnumnights=" + minnumnights +
            ", optimalPrice=" + optimalPrice +
            ", user=" + user +
            '}';
    }
}
