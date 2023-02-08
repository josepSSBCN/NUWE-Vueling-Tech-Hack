package org.hackVueling.control;

import org.hackVueling.model.dataStructrure.*;
import org.junit.jupiter.api.Test;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SearchControlClsTest {

    //region ATTRIBUTS
    List<AirTrip> airTripList;
    List<AirTrip> airTripEmptysList;
    List<LandTrip> landTripList;

    //endregion ATTRIBUTS

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        List<Flight> flightsTrip1 = new ArrayList<>();
        List<Flight> flightsTrip2 = new ArrayList<>();
        List<City> citesAirTrip1 = new ArrayList<>();
        List<City> citesAirTrip2 = new ArrayList<>();
        List<City> citesLandTrip1 = new ArrayList<>();
        List<City> citesLandTrip2 = new ArrayList<>();
        List<City> citesLandTrip3 = new ArrayList<>();
        List<Hotel> hotelsLandTrip1 = new ArrayList<>();
        List<Hotel> hotelsLandTrip2 = new ArrayList<>();
        List<Hotel> hotelsLandTrip3 = new ArrayList<>();
        airTripList = new ArrayList<>();
        landTripList = new ArrayList<>();
        airTripEmptysList = new ArrayList<>();

        //region AIR TRIP CONFIG
        flightsTrip1.addAll(Arrays.asList(
                new Flight((short) 1, (short) 1, "one City", new Time(10, 10, 00)),
                new Flight((short) 2, (short) 2, "two City", new Time(9, 10, 00))
        ));
        citesAirTrip1.addAll(Arrays.asList(
                new City((short) 3, "three City"),
                new City((short) 4, "for City"),
                new City((short) 5, "five City")
        ));

        flightsTrip2.addAll(Arrays.asList(
                new Flight((short) 1,(short) 7, "seven City", new Time(10, 10, 00)),
                new Flight((short) 2, (short) 8, "eight Citi", new Time(9, 10, 00))
        ));
        citesAirTrip2.addAll(Arrays.asList(
                new City((short) 3, "one City"),
                new City((short) 4, "two City"),
                new City((short) 5, "three City")
        ));

        airTripList.addAll(Arrays.asList(
                new AirTrip((short) 1, "Air Trip 1", (short) 3, flightsTrip1, citesAirTrip1),
                new AirTrip((short) 2, "Air Trip 2", (short) 5, flightsTrip2, citesAirTrip2)
        ));

        airTripEmptysList.addAll(Arrays.asList(
                new AirTrip((short) 3, "Air Trip 3", (short) 4, new ArrayList<>(), citesAirTrip2),
                new AirTrip((short) 4, "Air Trip 4", (short) 3, flightsTrip2, new ArrayList<>()),
                new AirTrip((short) 5, "Air Trip 5", (short) 8, new ArrayList<>(), new ArrayList<>())
        ));

        //endregion AIR TRIP CONFIG


        //region LAND TRIP CONFIG
        citesLandTrip1.addAll(Arrays.asList(
                new City((short) 1, "one City"),
                new City((short) 3, "three City"),
                new City((short) 7, "seven City")
        ));
        hotelsLandTrip1.addAll(Arrays.asList(
                new Hotel((short) 1, "Hotel One", (short) 1, "one City", (byte) 3),
                new Hotel((short) 2, "Hotel Two", (short) 3, "three City", (byte) 4),
                new Hotel((short) 3, "Hotel Three", (short) 7, "seven City", (byte) 3)
        ));
        citesLandTrip2.addAll(Arrays.asList(
                new City((short) 5, "five City"),
                new City((short) 8, "eight City"),
                new City((short) 10, "ten City")
        ));
        hotelsLandTrip2.addAll(Arrays.asList(
                new Hotel((short) 4, "Hotel For", (short) 5, "five City", (byte) 3),
                new Hotel((short) 5, "Hotel Five", (short) 8, "eight City", (byte) 4),
                new Hotel((short) 6, "Hotel Six", (short) 10, "ten City", (byte) 4)
        ));
        citesLandTrip3.addAll(Arrays.asList(
                new City((short) 2, "two City"),
                new City((short) 4, "for City"),
                new City((short) 6, "six City")
        ));
        hotelsLandTrip3.addAll(Arrays.asList(
                new Hotel((short) 7, "Hotel Seven", (short) 1, "one City", (byte) 6),
                new Hotel((short) 8, "Hotel Eight", (short) 3, "three City", (byte) 5),
                new Hotel((short) 9, "Hotel Night", (short) 7, "seven City", (byte) 6)
        ));

        landTripList.addAll(Arrays.asList(
                new LandTrip((short) 0, "Land trip 1", (short) 4, citesLandTrip1, hotelsLandTrip1),
                new LandTrip((short) 1, "Land trip 2", (short) 5, citesLandTrip2, hotelsLandTrip2),
                new LandTrip((short) 2, "Land trip 3", (short) 7, citesLandTrip3, hotelsLandTrip3)
        ));


        //endregion LAND TRIP CONFIG


    }

    /**
     * Test how show, empty lists & null lists.
     */
    @org.junit.jupiter.api.Test
    void resultConstruct() {
        List<AirTrip> airTripListLcl = new ArrayList<>();
        List<LandTrip> landTripListLcl = new ArrayList<>();
        SearchControlCls searchControlCls = new SearchControlCls();
        String text = "";

        // Check show
        System.out.println("WITH LAND AND AIR TRIPS");
        System.out.println(searchControlCls.resultConstruct(landTripList, airTripList));
        System.out.println("WITHOUT LAND TRIP RESLUTS");
        System.out.println(searchControlCls.resultConstruct(landTripListLcl, airTripList));
        System.out.println("WITHOUT AIR TRIP RESULTS");
        System.out.println(searchControlCls.resultConstruct(landTripListLcl, airTripList));
        System.out.println("WITHOUT AIR TRIP RESULTS");
        System.out.println(searchControlCls.resultConstruct(landTripListLcl, airTripList));
        System.out.println("AIR TRIP RESULTS: With Empty Lists");
        System.out.println(searchControlCls.resultConstruct(landTripList, airTripEmptysList));


        // Empty Lists
        assertEquals("We don't have trips with that city.\n Please, try with other city.",
                searchControlCls.resultConstruct(landTripListLcl, airTripListLcl));

        // Null lists
        assertEquals("We don't have trips with that city.\n Please, try with other city.",
                searchControlCls.resultConstruct(null, null));

    }


    @Test
    void findTripsWithCityName() {
        String text = "";
        SearchControlCls searchControlCls = new SearchControlCls();

        text = searchControlCls.findTripsWithCityName("one");

        System.out.println(text);

    }
}