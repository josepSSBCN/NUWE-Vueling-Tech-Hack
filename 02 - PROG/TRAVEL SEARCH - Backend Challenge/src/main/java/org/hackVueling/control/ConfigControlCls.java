package org.hackVueling.control;

import org.hackVueling.model.classes.ConfigModelCls;
import org.hackVueling.model.dataStructrure.*;

import java.sql.SQLException;
import java.sql.Time;
import java.util.List;

/**
 * Classe to control all actions of Configuration on Control layer.
 */
public class ConfigControlCls {
    //region ATTRIBUTES
    private static ConfigControlCls instance;
    private static ConfigModelCls configModelCls;

    //endregion ATTRIBUTES


    //region CONSTRUCTOR
    private ConfigControlCls() {
        configModelCls = ConfigModelCls.getInstance();
    }

    public static ConfigControlCls getInstance() {
        if (instance == null) {
            instance = new ConfigControlCls();
        }
        return instance;
    }

    //endregion CONSTRUCTOR


    //region METHODS ADD

    /**
     * Method to add a city on ddbb
     *
     * @param nameIn city name
     * @return 0 = city added ok; 1 = some error with DDBB; 2 = city exist; 3 = error adding city to ddbb;
     * 4 = City name is empty.
     */
    public int addCity(String nameIn) {
        //region DEFINITION VARIABLES
        int resul, existCity;
        City cityNew = new City();

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try {
            if (!nameIn.isEmpty()) {
                // CHECK IF CITY EXIST
                existCity = configModelCls.checkCityExist(nameIn, true);

                if (existCity == 0) {
                    cityNew.setName(nameIn);

                    // ADD CITY
                    resul = (configModelCls.addCity(cityNew)) ? 0 : 3;

                } else {
                    resul = 2;
                }
            } else {
                resul = 4;
            }

        } catch (Exception ex) {
            resul = 1;
        }

        //endregion ACTIONS


        // OUT
        return resul;

    }

    /**
     * Method to add a flight on ddbb.
     *
     * @param cityName      Name of departure city.
     * @param departureTime Departure time, format 'hh:mm'
     * @return 0 = added correctly; 1 = some error with DDBB; 2 = city no exist; 3 = error adding flight to ddbb.
     */
    public int addFlight(String cityName, String departureTime) {
        //region DEFINITION VARIABLES
        int resul, cityExist;
        Flight flight = new Flight();

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try {
            cityExist = configModelCls.checkCityExist(cityName, true);
            if (cityExist > 0) {
                // CREATE FLIGHT
                flight.setCityDeparture(cityName);
                flight.setIdCity((short) cityExist);
                flight.setDepartureTime(new Time(Integer.parseInt(departureTime.substring(0, 2)),
                        Integer.parseInt(departureTime.substring(3, 5)), 0));

                // ADD FLIGHT
                resul = (configModelCls.addFLight(flight)) ? 0 : 3;

            } else {
                resul = 2;
            }


        } catch (Exception ex) {
            resul = 1;
        }

        //endregion ACTIONS


        // OUT
        return resul;

    }

    /**
     * Method to add a new hotel to ddbb.
     *
     * @param hotelName  Hotel's name.
     * @param cityName   Name of hotel's city.
     * @param idCategory ID of hotel's category.
     * @return 0 = added correctly; 1 = some error with DDBB; 2 = city no exist; 3 = error adding hotel to ddbb.
     */
    public int addHotel(String hotelName, String cityName, int idCategory) {
        //region DEFINITION VARIABLES
        int resul, idCity;
        Hotel newHotel = new Hotel();

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try {
            idCity = configModelCls.checkCityExist(cityName, true);
            if (idCity != 0) {
                // CREATE FLIGHT
                newHotel.setName(hotelName);
                newHotel.setCityName(cityName);
                newHotel.setIdCategory((byte) idCategory);
                newHotel.setIdCity((short) idCity);

                // ADD HOTEL
                resul = (configModelCls.addHotel(newHotel)) ? 0 : 3;

            } else {
                resul = 2;
            }
        } catch (Exception ex) {
            resul = 3;
        }
        //endregion ACTIONS


        // OUT
        return resul;

    }

    /**
     * Method to manage the Air Trip Creation.
     *
     * @param airTripIn Air Trip classe, to create.
     * @param citiesNameListIn List of cities what Air Trip visited.
     * @return 0 = added correctly; 1 = some error with DDBB; 2 = city class is null; 3 = citisNameList empty
     */
    public int addAirTrip(AirTrip airTripIn, List<String> citiesNameListIn) {
        //region DEFINITION VARIABLES
        boolean addResul, exitDo = false;
        int resul = 0, step = 0;
        City newCity;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try {
            // INITIAL CHECKS
            if (citiesNameListIn == null) {
                resul = 2;
            } else if (citiesNameListIn.size() == 0) {
                resul = 3;
            } else {
                do {
                    switch (step) {
                        case 0 -> {         // CREATE CITY LIST, FROM CITIES NAMES
                            for (String n : citiesNameListIn) {
                                newCity = new City(configModelCls.getCity(n));
                                if (newCity != null) airTripIn.addCity(newCity);
                            }
                            step = 1;
                        }
                        case 1 -> {         // ADD NEW AIR TRIP ON DDBB
                            addResul = configModelCls.addAirTrip(airTripIn);
                            airTripIn.setId(new AirTrip(configModelCls.getAirTrip(airTripIn)).getId());
                            step = (airTripIn != null) ? ((addResul) ? 2 : 1000) : 1000;
                        }
                        case 2 -> {         // ADD LIST OF FLIGHTS ON DDBB
                            addResul = true; ////* configModelCls.addAirTripFlights(airTripIn);
                            step = (addResul) ? 3 : 1100;
                        }
                        case 3 -> {        // ADD LIST OF CITIES VISITED ON DDBB
                            addResul = configModelCls.addAirTripCities(airTripIn);
                            step = (addResul) ? 1100 : 1000;
                        }
                        case 1000 -> {      // SOME ERROR OCURRED
                            resul = 1;
                            exitDo = true;
                        }
                        case 1100 -> {      // ALL ADD CORRECTLY
                            resul = 0;
                            exitDo = true;
                        }
                        default -> {
                            resul = 1;
                            exitDo = true;
                        }
                    }
                } while (!exitDo);
            }
        } catch (SQLException e) {
            resul = 1;
        }
        //endregion ACTIONS


        // OUT
        return resul;

    }

    /**
     * Method to manage the Air Trip Creation.
     * @param landTripIn Land Trip classe, to create.
     * @param citiesNameListIn List of cities what Land Trip visited.
     * @return 0 = added correctly; 1 = some error with DDBB; 2 = city class is null; 3 = citisNameList empty
     */
    public int addLandTrip(LandTrip landTripIn, List<String> citiesNameListIn) {
        //region DEFINITION VARIABLES
        boolean addResul, exitDo = false;
        int resul = 0, step = 0;
        City newCity;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try {
            // INITIAL CHECKS
            if (citiesNameListIn == null) {
                resul = 2;
            } else if (citiesNameListIn.size() == 0) {
                resul = 3;
            } else {
                do {
                    switch (step) {
                        case 0 -> {         // CREATE CITY LIST, FROM CITIES NAMES
                            for (String n : citiesNameListIn) {
                                newCity = new City(configModelCls.getCity(n));
                                if (newCity != null) landTripIn.addCity(newCity);
                            }
                            step = 1;
                        }
                        case 1 -> {         // ADD NEW LAND TRIP ON DDBB
                            addResul = configModelCls.addLandTrip(landTripIn);
                            landTripIn.setId(new LandTrip(configModelCls.getLandTrip(landTripIn)).getId());
                            step = (addResul) ? 2 : 1000;
                        }
                        case 2 -> {         // ADD LIST OF FLIGHTS ON DDBB
                            addResul = true; ////* configModelCls.addAirTripFlights(airTripIn);
                            step = (addResul) ? 3 : 1100;
                        }
                        case 3 -> {        // ADD LIST OF CITIES VISITED ON DDBB
                            addResul = configModelCls.addLandTripCities(landTripIn);
                            step = (addResul) ? 1100 : 1000;
                        }
                        case 1000 -> {      // SOME ERROR OCURRED
                            resul = 1;
                            exitDo = true;
                        }
                        case 1100 -> {      // ALL ADD CORRECTLY
                            resul = 0;
                            exitDo = true;
                        }
                        default -> {
                            resul = 1;
                            exitDo = true;
                        }
                    }
                } while (!exitDo);
            }
        } catch (SQLException e) {
            resul = 1;
        }
        //endregion ACTIONS


        // OUT
        return resul;

    }

    //endregion METHODS ADD


    //region METHODS CHECKS

    /**
     * Method to check if the city name exist.
     *
     * @param cityName Name to city
     * @return 0 = city exist; 1 = some error with DDBB; 2 = city doesn't exist; 3 = city name is empty.
     */
    public int checkCity(String cityName) {
        //region DEFINITION VARIABLES
        int resul, existCity;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try {
            if (!cityName.isEmpty() && !cityName.isBlank()) {
                existCity = configModelCls.checkCityExist(cityName, true);

                resul = (existCity > 0) ? 0 : 2;
            } else {
                resul = 3;
            }
        } catch (Exception ex) {
            resul = 1;
        }

        //endregion ACTIONS


        // OUT
        return resul;

    }

    /**
     * Method to check if the trip name exist.
     *
     * @param tripName Name to city
     * @return 0 = trip name doesn't exist; 1 = some error with DDBB; 2 = trip exist; 3 = trip name is empty.
     */
    public int checkTrip(String tripName) {
        //region DEFINITION VARIABLES
        boolean existTrip;
        int resul;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try {
            if (!tripName.isEmpty() && !tripName.isBlank()) {
                existTrip = configModelCls.checkTripExist(tripName);

                resul = (existTrip) ? 2 : 0;
            } else {
                resul = 3;
            }
        } catch (Exception ex) {
            resul = 1;
        }

        //endregion ACTIONS


        // OUT
        return resul;

    }

    /**
     * Method to check if the hotel's name exist.
     *
     * @param hotelName Name to hotel.
     * @return 0 = hotel's name doesn't exist; 1 = some error with DDBB; 2 = hotel exist; 3 = hotel name is empty.
     */
    public int checkHotel(String hotelName) {
        //region DEFINITION VARIABLES
        boolean existHotel;
        int resul;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try {
            if (!hotelName.isEmpty() && !hotelName.isBlank()) {
                existHotel = configModelCls.checkHotelExist(hotelName);

                resul = (existHotel) ? 2 : 0;
            } else {
                resul = 3;
            }
        } catch (Exception ex) {
            resul = 1;
        }

        //endregion ACTIONS


        // OUT
        return resul;

    }

    //endregion METHODS CHECKS

}
