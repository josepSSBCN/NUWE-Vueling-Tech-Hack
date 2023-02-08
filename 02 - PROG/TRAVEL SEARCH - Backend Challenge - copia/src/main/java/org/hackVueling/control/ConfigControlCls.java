package org.hackVueling.control;

import org.hackVueling.model.classes.ConfigModelCls;
import org.hackVueling.model.dataStructrure.City;
import org.hackVueling.model.dataStructrure.Flight;
import org.hackVueling.model.dataStructrure.Hotel;

import java.sql.Time;
import java.time.LocalTime;

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


    //region GETTERS & SETTERS

    //endregion GETTERS & SETTERS


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
        boolean addedOk = false;
        int resul = 0, existCity;
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
                flight.setDepartureTime(new Time(Integer.parseInt(departureTime.substring(0, 1)),
                        Integer.parseInt(departureTime.substring(3, 4)), 00));

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
        boolean cityExist;
        int resul = 0, idCity = 0;
        Hotel newHotel = new Hotel();

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try {
            idCity = configModelCls.checkCityExist(cityName, true);
            if (idCity != 0) {
                // CREATE FLIGHT
                newHotel.setName(hotelName);
                newHotel.setCityName(cityName);
                newHotel.setCategory((byte) idCategory);
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

                resul = (existCity >0) ? 0 : 2;
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
        boolean existTrip = false, addedOk = false;
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
        boolean existHotel = false;
        int resul;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try {
            if (!hotelName.isEmpty() && !hotelName.isBlank()) {
                existHotel = configModelCls.checkTripExist(hotelName);

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
