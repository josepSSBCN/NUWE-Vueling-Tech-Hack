package org.hackVueling.model.dataStructrure;

import java.util.ArrayList;
import java.util.List;

/**
 * Structure classe to lands trips.
 */
public class LandTrip {
    //region ATTRIBUTES
    /**
     * Land Trip's id.
     */
    private short id;
    /**
     * Land trip's name.
     */
    private String name;
    /**
     * Days of duration trip.
     */
    private short tripDays;
    /**
     * List of the cities visited on trip.
     */
    private List<City> citiesVisited = new ArrayList<>();
    /**
     * List of the hotels of trip.
     */
    private List<Hotel> hotels = new ArrayList<>();

    //endregion ATTRIBUTES


    //region CONSTRUCTOR

    public LandTrip() {
    }

    public LandTrip(short id, String name, short tripDays, List<City> citiesVisited, List<Hotel> hotels) {
        this.id = id;
        this.name = name;
        this.tripDays = tripDays;
        this.citiesVisited = ((citiesVisited != null) && citiesVisited.size() > 0) ? new ArrayList<>(citiesVisited) : new ArrayList<>();
        this.hotels = ((hotels != null) && (hotels.size() > 0)) ? new ArrayList<>(hotels) : new ArrayList<>();
    }

    public LandTrip(LandTrip landTripsIn) {
        this.id = landTripsIn.id;
        this.name = landTripsIn.name;
        this.tripDays = landTripsIn.tripDays;
        this.citiesVisited = ((landTripsIn.citiesVisited != null) && (landTripsIn.citiesVisited.size() > 0)) ?
                new ArrayList<>(citiesVisited) : new ArrayList<>();
        this.hotels = ((landTripsIn.hotels != null) && (landTripsIn.hotels.size() > 0)) ?
                new ArrayList<>(landTripsIn.hotels) : new ArrayList<>();

    }


    //endregion CONSTRUCTOR


    //region GETTERS & SETTERS

    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public short getTripDays() {
        return tripDays;
    }

    public void setTripDays(short tripDays) {
        this.tripDays = tripDays;
    }

    public List<City> getCitiesVisited() {
        return citiesVisited;
    }

    public void setCitiesVisited(List<City> citiesVisited) {
        this.citiesVisited = citiesVisited;
    }

    public List<Hotel> getHotels() {
        return hotels;
    }

    public void setHotels(List<Hotel> hotels) {
        this.hotels = hotels;
    }

    //endregion GETTERS & SETTERS


    //region METHODS

    /**
     * Method to add a hotel on the list.
     *
     * @param hotelIn Hotel to add.
     */
    public void addHotel(Hotel hotelIn) {
        if (hotelIn != null) {
            this.hotels.add(hotelIn);
        }
    }

    /**
     * Method to add a city on the list.
     *
     * @param cityIn City to add.
     */
    public void addCity(City cityIn) {
        // todo s'ha de comprovar si ja existeix la ciutat?
        if (cityIn != null) {
            this.citiesVisited.add(cityIn);
        }
    }

    //endregion METHODS

}
