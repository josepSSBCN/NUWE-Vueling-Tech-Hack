package org.hackVueling.model.dataStructrure;

import java.sql.Time;

/**
 * Structure classe to flights.
 */
public class Flight {
    //region ATTRIBUTES
    private short id;
    private short idCity;
    private String cityDeparture;
    private Time departureTime;

    //endregion ATTRIBUTES


    //region CONSTRUCTOR

    public Flight() {
    }

    public Flight(short id, short idCity, String cityDeparture, Time departureTime) {
        this.id = id;
        this.idCity = idCity;
        this.cityDeparture = cityDeparture;
        this.departureTime = departureTime;
    }

    public Flight(Flight flightIn) {
        this.id = flightIn.id;
        this.idCity = flightIn.idCity;
        this.cityDeparture = flightIn.cityDeparture;
        this.departureTime = flightIn.departureTime;
    }

    //endregion CONSTRUCTOR


    //region GETTERS & SETTERS

    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    public short getIdCity() {
        return idCity;
    }

    public void setIdCity(short idCity) {
        this.idCity = idCity;
    }

    public String getCityDeparture() {
        return cityDeparture;
    }

    public void setCityDeparture(String cityDeparture) {
        this.cityDeparture = cityDeparture;
    }

    public Time getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Time departureTime) {
        this.departureTime = departureTime;
    }

    //endregion GETTERS & SETTERS

}
