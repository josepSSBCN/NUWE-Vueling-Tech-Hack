package org.hackVueling.model.classes;

import org.hackVueling.model.dataStructrure.*;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ConfigModelClsTest {

    @Test
    void addCity() {
        ConfigModelCls configModelCls = ConfigModelCls.getInstance();

        try {
            assertTrue(configModelCls.addCity(new City((short) 0, "Barcelona")));

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Test
    void addHotel() {
        ConfigModelCls configModelCls = ConfigModelCls.getInstance();

        try {
            assertTrue(configModelCls.addHotel(new Hotel((short) 0, "Hotel Barcelona", (short) 11, "Barcelona", (byte) 5)));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void addFLight() {
        ConfigModelCls configModelCls = ConfigModelCls.getInstance();

        try {
            assertTrue(configModelCls.addFLight(new Flight((short) 0, (short) 11, "Barcelona",
                    new Time(8, 45, 0))));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void addAirTrip() {
        ConfigModelCls configModelCls = ConfigModelCls.getInstance();

        try {
            assertTrue(configModelCls.addAirTrip(new AirTrip((short) 0, "Catalonia Exclusive",
                    (short) 10, new ArrayList<>(), new ArrayList<>())));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void addLandTrip() {
        ConfigModelCls configModelCls = ConfigModelCls.getInstance();

        try {
            assertTrue(configModelCls.addLandTrip(new LandTrip((short) 0, "Secret Catalonia",
                    (short) 7, new ArrayList<>(), new ArrayList<>())));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void addLandTripCities() {
        ConfigModelCls configModelCls = ConfigModelCls.getInstance();

        try {
            List<City> cityList = new ArrayList<>(Arrays.asList(
                    new City((short) 1, "City One"),
                    new City((short) 2, "City Two")
            ));

            assertTrue(configModelCls.addLandTripCities(new LandTrip((short) 5, "Secret Catalonia",
                    (short) 7, cityList, new ArrayList<>())));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void addLandTripHotels() {
        ConfigModelCls configModelCls = ConfigModelCls.getInstance();

        try {
            List<Hotel> hotelList = new ArrayList<>(Arrays.asList(
                    new Hotel((short) 2, "Hotel 11", (short) 11, "Barclona", (byte) 3),
                    new Hotel((short) 4, "Hotel 12", (short) 11, "Barclona", (byte) 6)
            ));

            assertTrue(configModelCls.addLandTripHotels(new LandTrip((short) 5, "Secret Catalonia",
                    (short) 7, new ArrayList<>(), hotelList)));

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Test
    void addAirTripCities() {
        ConfigModelCls configModelCls = ConfigModelCls.getInstance();

        try {
            List<City> cityList = new ArrayList<>(Arrays.asList(
                    new City((short) 6, "City Six"),
                    new City((short) 7, "City Seven")
            ));

            assertTrue(configModelCls.addAirTripCities(new AirTrip((short) 5, "Catalonia Exclusive",
                    (short) 7, new ArrayList<>(), cityList)));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void addAirTripFlights() {
        ConfigModelCls configModelCls = ConfigModelCls.getInstance();

        try {
            List<Flight> flightList = new ArrayList<>(Arrays.asList(
                    new Flight((short) 10, (short) 6, "City Six", new Time(5, 33, 0)),
                    new Flight((short) 11, (short) 7, "City Seven", new Time(18, 53, 0))
            ));

            assertEquals(true, configModelCls.addAirTripFlights(new AirTrip((short)5, "Catalonia Exclusive",
                    (short)7, flightList, new ArrayList<>())));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
