package org.hackVueling.control;

import org.hackVueling.model.classes.SearchModelCls;
import org.hackVueling.model.dataStructrure.*;
import org.hackVueling.model.enums.EnumCategorys;
import org.hackVueling.model.enums.EnumTrips;
import org.hackVueling.view.ToolsViewCls;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe to control all actions of Search on Control layer.
 */
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


    //region METHODS
    public String findTripsWithCityName(String citiNameIn) {
        //region DEFINITION VARIABLES
        boolean resulSec;
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
                    // Get air trip's Id with city name
                    airTripsIdList.addAll(searchModelCls.getAirTripsWitCity(citiNameIn));

                    if (airTripsIdList.size() > 0) {
                        // Get air trips with city name
                        airTripList.addAll(searchModelCls.getAirTripOfIdList(airTripsIdList));
                    }

                    // Get land trips with city name
                    landTripsIdList.addAll(searchModelCls.getLandTripsWitCity(citiNameIn));

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
                            lt.getName(), EnumTrips.LandTrip.name, lt.getTripDays()));

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
                                    EnumCategorys.nameOfId(h.getIdCategory()));
                        }
                        text = (text.endsWith(", ")) ? text.substring(0, text.length() - 2) : text;
                    }
                    text = text.concat("\n");

                }

                // CONSTRUCT AIR TRIP RESULT
                for (AirTrip at : airTripsIn) {
                    // Add initial info
                    text = text.concat(String.format("%s; %s; %s days; ",
                            at.getName(), EnumTrips.AirTrip.name, at.getTripDays()));

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
            ToolsViewCls.pressAnyKey(ToolsViewCls.getLangText(2000));
        }

        //endregion ACTIONS


        // OUT
        return text;

    }

    //endregion METHODS

}
