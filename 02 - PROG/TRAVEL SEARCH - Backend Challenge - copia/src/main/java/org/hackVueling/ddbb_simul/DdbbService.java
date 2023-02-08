package org.hackVueling.ddbb_simul;

import org.hackVueling.model.dataStructrure.*;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Classe to simulation ddbb
 */
public class DdbbService {

    //region ATTRIBUTES
    private static DdbbService instance;

    //region List Simulation DDBB Tables
    private List<AirTrip> airTripList;
    private List<City> cityList;
    private List<Flight> flightsList;
    private List<Hotel> hotelList;
    private List<LandTrip> landTripList;

    //endregion List Simulation DDBB Tables

    //endregion ATTRIBUTES


    //region CONSTRUCTOR
    private DdbbService() {
        airTripList = new ArrayList<>();
        cityList = new ArrayList<>();
        flightsList = new ArrayList<>();
        hotelList = new ArrayList<>();
        landTripList = new ArrayList<>();

        FillLists();
    }

    public static DdbbService getInstance() {
        if (instance == null) {
            instance = new DdbbService();
        }
        return instance;
    }

    //endregion CONSTRUCTOR


    //region GETTERS & SETTERS

    public List<AirTrip> getAirTripList() {
        return airTripList;
    }

    public List<City> getCityList() {
        return cityList;
    }

    public List<Flight> getFlightsList() {
        return flightsList;
    }

    public List<Hotel> getHotelList() {
        return hotelList;
    }

    public List<LandTrip> getLandTripList() {
        return landTripList;
    }


    //endregion GETTERS & SETTERS


    //region METHODS PUBLIC

    //endregion METHODS PUBLIC


    //region METHODS PRIVATE
    private void FillLists() {

        //region ATTRIBUTES
        List<City> airTrip1Cities, airTrip2Cities, airTrip3Cities, airTrip4Cities;
        List<City> landTrips1Cities, landTrips2Cities, landTrips3Cities, landTrips4Cities;
        List<Flight> airTrip1Flights, airTrip2Flights, airTrip3Flights, airTrip4Flights;
        List<Hotel> landTrips1Hotel, landTrips2Hotel, landTrips3Hotel, landTrips4Hotel;

        //endregion ATTRIBUTES


        //region CITIES LIST
        cityList.addAll(Arrays.asList(
                new City((short) 1, "City One"),
                new City((short) 2, "City Two"),
                new City((short) 3, "City Three"),
                new City((short) 4, "City Fort"),
                new City((short) 5, "City Five"),
                new City((short) 6, "City Six"),
                new City((short) 7, "City Seven"),
                new City((short) 8, "City Eight"),
                new City((short) 9, "City Nine"),
                new City((short) 10, "City Ten"),
                // Assian cities
                new City((short) 11, "十一城"),
                new City((short) 12, "十二城"),
                new City((short) 13, "十三城"),
                // Arabian cities
                new City((short) 14, "مدينة أربعة عشر"),
                new City((short) 15, "مدينة خمسة عشر"),
                new City((short) 16, "ستة عشر خمسة عشر")
        ));

        airTrip1Cities = new ArrayList<>(Arrays.asList(cityList.get(1), cityList.get(2)));
        airTrip2Cities = new ArrayList<>(Arrays.asList(cityList.get(3), cityList.get(4)));
        airTrip3Cities = new ArrayList<>(Arrays.asList(cityList.get(5), cityList.get(6)));
        airTrip4Cities = new ArrayList<>(Arrays.asList(cityList.get(7), cityList.get(8)));

        landTrips1Cities = new ArrayList<>(Arrays.asList(cityList.get(8), cityList.get(3)));
        landTrips2Cities = new ArrayList<>(Arrays.asList(cityList.get(2), cityList.get(5)));
        landTrips3Cities = new ArrayList<>(Arrays.asList(cityList.get(4), cityList.get(7)));
        landTrips4Cities = new ArrayList<>(Arrays.asList(cityList.get(6), cityList.get(1)));

        //endregion CITIES LIST


        //region HOTELS LIST
        hotelList.addAll(Arrays.asList(
                new Hotel((short) 1, "Hotel One", cityList.get(0).getId(), cityList.get(0).getName(), (byte) 3),
                new Hotel((short) 2, "Hotel Two", cityList.get(1).getId(), cityList.get(1).getName(), (byte) 2),
                new Hotel((short) 3, "Hotel Three", cityList.get(2).getId(), cityList.get(2).getName(), (byte) 4),
                new Hotel((short) 4, "Hotel Fort", cityList.get(3).getId(), cityList.get(3).getName(), (byte) 5),
                new Hotel((short) 5, "Hotel Five", cityList.get(4).getId(), cityList.get(4).getName(), (byte) 6),
                new Hotel((short) 6, "Hotel Six", cityList.get(5).getId(), cityList.get(5).getName(), (byte) 4),
                new Hotel((short) 7, "Hotel Seven", cityList.get(6).getId(), cityList.get(6).getName(), (byte) 3),
                new Hotel((short) 8, "Hotel Eight", cityList.get(7).getId(), cityList.get(7).getName(), (byte) 2),
                new Hotel((short) 9, "Hotel Nine", cityList.get(8).getId(), cityList.get(8).getName(), (byte) 1),
                new Hotel((short) 10, "Hotel Ten", cityList.get(9).getId(), cityList.get(9).getName(), (byte) 1)
        ));

        landTrips1Hotel = new ArrayList<>(Arrays.asList(hotelList.get(8), hotelList.get(3)));
        landTrips2Hotel = new ArrayList<>(Arrays.asList(hotelList.get(2), hotelList.get(5)));
        landTrips3Hotel = new ArrayList<>(Arrays.asList(hotelList.get(4), hotelList.get(7)));
        landTrips4Hotel = new ArrayList<>(Arrays.asList(hotelList.get(6), hotelList.get(1)));

        //endregion HOTELS LIST


        //region FLIGHTS LIST
        flightsList.addAll(Arrays.asList(
                new Flight((short) 1, cityList.get(0).getId(), cityList.get(0).getName(), new Time(9, 10, 00)),
                new Flight((short) 2, cityList.get(0).getId(), cityList.get(1).getName(), new Time(10, 1, 00)),
                new Flight((short) 3, cityList.get(0).getId(), cityList.get(2).getName(), new Time(13, 13, 00)),
                new Flight((short) 4, cityList.get(0).getId(), cityList.get(3).getName(), new Time(16, 16, 00)),
                new Flight((short) 5, cityList.get(0).getId(), cityList.get(4).getName(), new Time(8, 37, 00)),
                new Flight((short) 6, cityList.get(0).getId(), cityList.get(5).getName(), new Time(7, 15, 00)),
                new Flight((short) 7, cityList.get(0).getId(), cityList.get(6).getName(), new Time(20, 45, 00)),
                new Flight((short) 8, cityList.get(0).getId(), cityList.get(7).getName(), new Time(17, 9, 00)),
                new Flight((short) 9, cityList.get(0).getId(), cityList.get(8).getName(), new Time(18, 56, 00)),
                new Flight((short) 10, cityList.get(0).getId(), cityList.get(9).getName(), new Time(23, 22, 00))
        ));

        airTrip1Flights = new ArrayList<>(Arrays.asList(flightsList.get(0), flightsList.get(8)));
        airTrip2Flights = new ArrayList<>(Arrays.asList(flightsList.get(9), flightsList.get(7)));
        airTrip3Flights = new ArrayList<>(Arrays.asList(flightsList.get(1), flightsList.get(6)));
        airTrip4Flights = new ArrayList<>(Arrays.asList(flightsList.get(2), flightsList.get(5)));

        //endregion FLIGHTS LIST


        //region AIR TRIP LIST
        airTripList.addAll(Arrays.asList(
                new AirTrip((short) 1, "Air Trip 1", (short) 7, airTrip1Flights, airTrip1Cities),
                new AirTrip((short) 2, "Air Trip 2", (short) 9, airTrip2Flights, airTrip2Cities),
                new AirTrip((short) 3, "Air Trip 3", (short) 10, airTrip3Flights, airTrip3Cities),
                new AirTrip((short) 4, "Air Trip 4", (short) 8, airTrip4Flights, airTrip4Cities)
        ));

        //endregion AIR TRIP LIST


        //region LAND TRIP LIST
        landTripList.addAll(Arrays.asList(
                new LandTrip((short) 1, "Land Trip 1", (short) 4, landTrips1Cities, landTrips1Hotel),
                new LandTrip((short) 2, "Land Trip 2", (short) 5, landTrips2Cities, landTrips2Hotel),
                new LandTrip((short) 3, "Land Trip 3", (short) 6, landTrips3Cities, landTrips3Hotel),
                new LandTrip((short) 4, "Land Trip 4", (short) 4, landTrips4Cities, landTrips4Hotel)
        ));

        //endregion LAND TRIP LIST


    }

    //endregion METHODS PRIVATE

}
