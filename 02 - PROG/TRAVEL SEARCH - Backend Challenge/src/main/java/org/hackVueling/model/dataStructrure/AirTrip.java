package org.hackVueling.model.dataStructrure;

import java.util.ArrayList;
import java.util.List;

/**
 * Structure classe to Air Trips.
 */
public class AirTrip {
    //region ATTRIBUTES
    /**
     * Trip's id.
     */
    private short id;
    /**
     * Trip's name.
     */
    private String name;
    /**
     * Days to duration trip.
     */
    private short tripDays;
    /**
     * List of flights of trip.
     */
    private List<Flight> flights = new ArrayList<>();
    /**
     * List of cities visited in trip.
     */
    private List<City> citiesVisited = new ArrayList<>();

    //endregion ATTRIBUTES


    //region CONSTRUCTOR

    public AirTrip() {

    }

    public AirTrip(short id, String name, short tripDays, List<Flight> flights, List<City> citiesVisited) {
        this.id = id;
        this.name = name;
        this.tripDays = tripDays;
        this.flights = ((flights != null) && (flights.size() > 0)) ? new ArrayList<>(flights) : new ArrayList<>();
        this.citiesVisited = ((citiesVisited != null) && (citiesVisited.size() > 0)) ? new ArrayList<>(citiesVisited) : new ArrayList<>();
    }

    public AirTrip(AirTrip airTripIn) {
        this.id = airTripIn.id;
        this.name = airTripIn.name;
        this.tripDays = airTripIn.tripDays;
        this.flights = ((airTripIn.flights != null) && (airTripIn.flights.size() > 0)) ?
                new ArrayList<>(flights) : new ArrayList<>();
        this.citiesVisited = ((airTripIn.citiesVisited != null) && (airTripIn.citiesVisited.size() > 0))
                ? new ArrayList<>(citiesVisited) : new ArrayList<>();
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
        if (flightIn != null) {
            this.flights.add(flightIn);
        }
    }

    //endregion METHODS


}
