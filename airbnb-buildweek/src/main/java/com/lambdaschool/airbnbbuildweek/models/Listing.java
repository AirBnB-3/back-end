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
    private String roomtype;

    private String neighbourhood;

    private int minnumnights;

    private int accomodates;

    private double optimalPrice;

    @ManyToOne
    @JoinColumn(name = "userid",
        nullable = false)
    @JsonIgnoreProperties(value = "listings",
        allowSetters = true)
    private User user;

    public Listing()
    {
    }

    public Listing(
        @NotNull String roomtype,
        String neighbourhood,
        int minnumnights,
        int accomodates,
        double optimalPrice)
    {
        this.roomtype = roomtype;
        this.neighbourhood = neighbourhood;
        this.minnumnights = minnumnights;
        this.accomodates = accomodates;
        this.optimalPrice = optimalPrice;
    }

    public Listing(
        @NotNull User user,
        @NotNull String roomtype,
        String neighbourhood,
        int minnumnights,
        int accomodates,
        double optimalPrice)
    {
        this.user = user;
        this.roomtype = roomtype;
        this.neighbourhood = neighbourhood;
        this.minnumnights = minnumnights;
        this.accomodates = accomodates;
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

    public String getRoomtype()
    {
        return roomtype;
    }

    public void setRoomtype(String roomtype)
    {
        this.roomtype = roomtype;
    }

    public String getNeighbourhood()
    {
        return neighbourhood;
    }

    public void setNeighbourhood(String neighbourhood)
    {
        this.neighbourhood = neighbourhood;
    }

    public int getMinnumnights()
    {
        return minnumnights;
    }

    public void setMinnumnights(int minnumnights)
    {
        this.minnumnights = minnumnights;
    }

    public int getAccomodates()
    {
        return accomodates;
    }

    public void setAccomodates(int accomodates)
    {
        this.accomodates = accomodates;
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
            ", roomtype='" + roomtype + '\'' +
            ", neighbourhood='" + neighbourhood + '\'' +
            ", minnumnights=" + minnumnights +
            ", accomodates=" + accomodates +
            ", optimalPrice=" + optimalPrice +
            ", user=" + user +
            '}';
    }
}
