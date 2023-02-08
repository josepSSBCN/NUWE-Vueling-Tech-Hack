package org.hackVueling.model.dataStructrure;

import java.util.ArrayList;
import java.util.List;

/**
 * Structure classe to Air Trips.
 */
public class AirTrip {
    //region ATTRIBUTES
    private short id;
    private String name;
    private short tripDays;
    private List<Flight> flights;
    private List<City> citiesVisited;

    //endregion ATTRIBUTES


    //region CONSTRUCTOR

    public AirTrip() {
    }

    public AirTrip(short id, String name, short tripDays, List<Flight> flights, List<City> citiesVisited) {
        this.id = id;
        this.name = name;
        this.tripDays = tripDays;
        this.flights = new ArrayList<>(flights);
        this.citiesVisited = new ArrayList<>(citiesVisited);
    }

    public AirTrip(AirTrip airTrip) {
        this.id = airTrip.id;
        this.name = airTrip.name;
        this.tripDays = airTrip.tripDays;
        this.flights = new ArrayList<>(airTrip.flights);
        this.citiesVisited = new ArrayList<>(citiesVisited);
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

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

    public List<City> getCitiesVisited() {
        return citiesVisited;
    }

    public void setCitiesVisited(List<City> citiesVisited) {
        this.citiesVisited = citiesVisited;
    }

    //endregion GETTERS & SETTERS


    //region METHODS

    /**
     * Method to add a city on the cities list.
     *
     * @param cityIn City to add.
     */
    public void addCity(City cityIn) {
        // TODO s'ha de comprovar si ja existeix la ciutat?
        if (cityIn != null) {
            this.citiesVisited.add(cityIn);
        }
    }

    /**
     * Method to add a flight on the flights list.
     *
     * @param flightIn Flight to add.
     */
    public void addFlight(Flight flightIn) {
        //TODO s'ha de comprovar si ja existeix el vol?
        if (flightIn != null) {
            this.flights.add(flightIn);
        }
    }

    //endregion METHODS


}
