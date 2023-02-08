package org.hackVueling.model.dataStructrure;

import java.util.ArrayList;
import java.util.List;

/**
 * Structure classe to lands trips.
 */
public class LandTrip {
    //region ATTRIBUTES
    private short id;
    private String name;
    private short tripDays;
    private List<City> citiesVisited;
    private List<Hotel> hotels;

    //endregion ATTRIBUTES


    //region CONSTRUCTOR

    public LandTrip() {
    }

    public LandTrip(short id, String name, short tripDays, List<City> citiesVisited, List<Hotel> hotels) {
        this.id = id;
        this.name = name;
        this.tripDays = tripDays;
        this.citiesVisited = new ArrayList<>(citiesVisited);
        this.hotels = new ArrayList<>(hotels);
    }

    public LandTrip(LandTrip landTripsIn) {
        this.id = landTripsIn.id;
        this.name = landTripsIn.name;
        this.tripDays = landTripsIn.tripDays;
        this.citiesVisited = new ArrayList<>(landTripsIn.citiesVisited);

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
     * @param hotelIn Hotel to add.
     */
    public void addHotel(Hotel hotelIn){
        if (hotelIn != null){
            this.hotels.add(hotelIn);
        }
    }

    /**
     * Method to add a city on the list.
     * @param cityIn City to add.
     */
    public void addCity(City cityIn){
        // todo s'ha de comprovar si ja existeix la ciutat?
        if(cityIn != null){
            this.citiesVisited.add(cityIn);
        }
    }

    //endregion METHODS

}
