package org.hackVueling.control;

import org.hackVueling.model.classes.SearchModelCls;
import org.hackVueling.model.dataStructrure.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchControlCls {
    //region ATTRIBUTES
    private static SearchControlCls instance;
    private static SearchModelCls searchModelCls;

    //endregion ATTRIBUTES


    //region CONSTRUCTOR
    private SearchControlCls() {
        searchModelCls = SearchModelCls.getInstance();
    }

    public static SearchControlCls getInstance() {
        if (instance == null) {
            instance = new SearchControlCls();
        }
        return instance;
    }

    //endregion CONSTRUCTOR


    //region GETTERS & SETTERS

    //endregion GETTERS & SETTERS


    //region METHODS
    public String findTripsWithCityName(String citiNameIn) {
        //region DEFINITION VARIABLES
        boolean resulMain = false, resulSec;
        String text = "";
        List<Short> airTripsIdList = new ArrayList<>();
        List<Short> landTripsIdList = new ArrayList<>();
        List<LandTrip> landTripList = new ArrayList<>();
        List<AirTrip> airTripList = new ArrayList<>();


        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try {
            // INIT

            // Checks
            if (!citiNameIn.isEmpty()) {
                // Check if the city exist on city table
                resulSec = searchModelCls.checkCityExist(citiNameIn);
                if (resulSec) {
                    // Get air trips Id with city name
                    airTripsIdList.addAll(searchModelCls.getAirTripsWitCity(citiNameIn));

                    if (airTripsIdList.size() > 0) {
                        // Get air trips with city name
                        airTripList.addAll(searchModelCls.getAirTripOfIdList(airTripsIdList));
                    }

                    // Get land trips Id with city name
                    landTripsIdList.addAll(searchModelCls.getAirTripsWitCity(citiNameIn));

                    if (landTripsIdList.size() > 0) {
                        // Get land trips with city name
                        landTripList.addAll(searchModelCls.getLandTripOfIdList(landTripsIdList));
                    }

                    // Construct the result
                    if (airTripList.size() > 0 || landTripList.size() > 0) {
                        text = resultConstruct(landTripList, airTripList);
                    }
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        //endregion ACTIONS


        // OUT
        return text;

    }

    public String resultConstruct(List<LandTrip> landTripsIn, List<AirTrip> airTripsIn) {
        //region DEFINITION VARIABLES
        String text = "";

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try {
            // CHECKS
            if (((landTripsIn == null) || airTripsIn == null) || (landTripsIn.size() == 0 && airTripsIn.size() == 0)) {
                text = "We don't have trips with that city.\n Please, try with other city.";
            } else {
                // CONSTRUCT LAND RESULT
                for (LandTrip lt : landTripsIn) {
                    // Add initial info
                    text = text.concat(String.format("%s; %s; %s days; ",
                            lt.getName(), BasicStructs.enumTrips.LandTrip.name, lt.getTripDays()));

                    // Add cities
                    if (!lt.getCitiesVisited().isEmpty()) {
                        for (City c : lt.getCitiesVisited()) {
                            text = text.concat(String.format("%s, ", c.getName()));
                        }
                        text = (text.endsWith(", ")) ? text.substring(0, text.length() - 2) + "; " : text + "; ";
                    }

                    // Add hotels
                    if (!lt.getHotels().isEmpty()) {
                        for (Hotel h : lt.getHotels()) {
                            text = text + String.format("%s %s, ", h.getName(),
                                    BasicStructs.enumCategorys.nameOfId(h.getCategory()));
                        }
                        text = (text.endsWith(", ")) ? text.substring(0, text.length() - 2) : text;
                    }
                    text = text.concat("\n");

                }

                // CONSTRUCT AIR TRIP RESULT
                for (AirTrip at : airTripsIn) {
                    // Add initial info
                    text = text.concat(String.format("%s; %s; %s days; ",
                            at.getName(), BasicStructs.enumTrips.AirTrip.name, at.getTripDays()));

                    // Add cities
                    if (!at.getCitiesVisited().isEmpty()) {
                        for (City c : at.getCitiesVisited()) {
                            text = text.concat(String.format("%s, ", c.getName()));
                        }
                        text = (text.endsWith(", ")) ? text.substring(0, text.length() - 2) + "; " : text + "; ";
                    }

                    // Add flights
                    if (!at.getFlights().isEmpty()) {
                        for (Flight f : at.getFlights()) {
                            text = text.concat(String.format("%s %s, "
                                    , f.getCityDeparture(), new SimpleDateFormat("HH:mm").format(f.getDepartureTime())));
                        }
                        text = (text.endsWith(", ")) ? text.substring(0, text.length() - 2) : text;
                    }
                    text = text.concat("\n");

                }
            }

        } catch (Exception ex) {
            // TODO thinking what todo
        }

        //endregion ACTIONS


        // OUT
        return text;


    }

    //endregion METHODS


}
