package org.hackVueling.model.classes;

import org.hackVueling.model.dataStructrure.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Classe to control all actions of Configuration on Model layer.
 */
public class ConfigModelCls {
    //region ATTRIBUTES
    private static ConfigModelCls instance;
    private static PreparedStatement ps;
    private static ResultSet rs;

    //endregion ATTRIBUTES


    //region CONSTRUCTOR
    private ConfigModelCls() {

    }

    public static ConfigModelCls getInstance() {
        if (instance == null) {
            instance = new ConfigModelCls();
        }
        return instance;
    }

    //endregion CONSTRUCTOR


    //region METHODS: ADD

    /**
     * Method to add an air trip on ddbb.
     *
     * @param airTripIn AirTrip classe to add.
     * @return false = doesn't add; true = added correctly.
     * @throws SQLException Throws if some problem with SQL connection.
     */
    public boolean addAirTrip(AirTrip airTripIn) throws SQLException {
        //region DEFINITION VARIABLES
        boolean resul;
        String sql1;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        // PREPARE & EXECUTE QUERY
        sql1 = "insert into airtrip (name_airTrip, days_airTrip) values (?, ?)";
        ps = Connect.getconnectionAdmin().prepareStatement(sql1);
        ps.setString(1, airTripIn.getName());
        ps.setShort(2, airTripIn.getTripDays());
        resul = ps.executeUpdate() > 0;

        //endregion ACTIONS


        // OUT
        return resul;

    }

    /**
     * Method to add a city on DDBB.
     *
     * @param city City classe to add.
     * @return false = doesn't add; true = added correctly.
     * @throws SQLException Throws if some problem with SQL connection.
     */
    public boolean addCity(City city) throws SQLException {
        //region DEFINITION VARIABLES
        boolean resul;
        String sql1;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        // PREPARE & EXECUTE QUERY
        sql1 = "insert into cities (name_cities) values (?)";
        ps = Connect.getconnectionAdmin().prepareStatement(sql1);
        ps.setString(1, city.getName());
        resul = ps.executeUpdate() > 0;

        //endregion ACTIONS


        // OUT
        return resul;

    }

    /**
     * Method to add a flight on ddbb.
     *
     * @param flightIn Flight classe to add.
     * @return false = doesn't add; true = added correctly.
     * @throws SQLException Throws if some problem with SQL connection.
     */
    public boolean addFLight(Flight flightIn) throws SQLException {
        //region DEFINITION VARIABLES
        boolean resul;
        String sql1;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        // PREPARE & EXECUTE QUERY
        sql1 = "insert into flights (Id_cities_flights, departureTime_flights) values (?, ?)";
        ps = Connect.getconnectionAdmin().prepareStatement(sql1);

        ps.setShort(1, flightIn.getIdCity());
        ps.setTime(2, flightIn.getDepartureTime());
        resul = ps.executeUpdate() > 0;

        //endregion ACTIONS


        // OUT
        return resul;

    }

    /**
     * Method to add a hotel on ddbb.
     *
     * @param hotelIn Hotel classe to add.
     * @return false = doesn't add; true = added correctly.
     * @throws SQLException Throws if some problem with SQL connection.
     */
    public boolean addHotel(Hotel hotelIn) throws SQLException {
        //region DEFINITION VARIABLES
        boolean resul;
        String sql1;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        // PREPARE & EXECUTE QUERY
        sql1 = "insert into hotels (Id_cities_hotels, name_hotels, idCategory_hotels) values (?, ?, ?)";
        ps = Connect.getconnectionAdmin().prepareStatement(sql1);
        ps.setShort(1, hotelIn.getIdCity());
        ps.setString(2, hotelIn.getName());
        ps.setByte(3, hotelIn.getIdCategory());
        resul = ps.executeUpdate() > 0;

        //endregion ACTIONS


        // OUT
        return resul;

    }

    /**
     * Method to add a land trip on ddbb.
     *
     * @param landTripIn Land Trip classe to add.
     * @return false = doesn't add; true = added correctly.
     * @throws SQLException Throws if some problem with SQL connection.
     */
    public boolean addLandTrip(LandTrip landTripIn) throws SQLException {
        //region DEFINITION VARIABLES
        boolean resul;
        String sql1;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        // PREPARE & EXECUTE QUERY
        sql1 = "insert into landtrip (name_landTrip, days_landTrip) values (?, ?)";
        ps = Connect.getconnectionAdmin().prepareStatement(sql1);
        ps.setString(1, landTripIn.getName());
        ps.setShort(2, landTripIn.getTripDays());
        resul = ps.executeUpdate() > 0;

        //endregion ACTIONS


        // OUT
        return resul;

    }

    /**
     * Method to add a cities visited of land trip on ddbb.
     *
     * @param landTripIn landTrip classe, with cities list to add.
     * @return false = doesn't add; true = added correctly.
     * @throws SQLException Throws if some problem with SQL connection.
     */
    public boolean addLandTripCities(LandTrip landTripIn) throws SQLException {
        //region DEFINITION VARIABLES
        boolean resul;
        String sql1;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        // PREPARE & EXECUTE QUERY
        sql1 = "insert into citieslandtrip (id_landTrip_citieslandtrip, id_cities_citieslandtrip) values ";
        for (City c : landTripIn.getCitiesVisited()) {
            sql1 = sql1.concat(String.format("(%s,%s),", landTripIn.getId(), c.getId()));
        }
        sql1 = sql1.substring(0, sql1.length() - 1).concat(";");

        ps = Connect.getconnectionAdmin().prepareStatement(sql1);
        resul = ps.executeUpdate() > 0;

        //endregion ACTIONS


        // OUT
        return resul;

    }

    /**
     * Method to add a hotels visited of land trip on ddbb.
     *
     * @param landTripIn LandTrip classe, with hotels list to add.
     * @return False = doesn't add; true = added correctly.
     * @throws SQLException Throws if some problem with SQL connection.
     */
    public boolean addLandTripHotels(LandTrip landTripIn) throws SQLException {
        //region DEFINITION VARIABLES
        boolean resul;
        String sql1;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        // PREPARE & EXECUTE QUERY
        sql1 = "insert into hotelslandtrip (id_hotels_hotelslandtrip, id_landTrip_hotelslandtrip) values ";
        for (Hotel h : landTripIn.getHotels()) {
            sql1 = sql1.concat(String.format("(%s,%s),", h.getId(), landTripIn.getId()));
        }
        sql1 = sql1.substring(0, sql1.length() - 1).concat(";");

        ps = Connect.getconnectionAdmin().prepareStatement(sql1);
        resul = ps.executeUpdate() > 0;

        //endregion ACTIONS


        // OUT
        return resul;

    }

    /**
     * Method to add a cities visited of air trip on ddbb.
     *
     * @param airTripIn AirTrip classe, with cities list to add.
     * @return False = doesn't add; true = added correctly.
     * @throws SQLException Throws if some problem with SQL connection.
     */
    public boolean addAirTripCities(AirTrip airTripIn) throws SQLException {
        //region DEFINITION VARIABLES
        boolean resul;
        String sql1;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        // PREPARE & EXECUTE QUERY
        if (airTripIn.getCitiesVisited().size() > 0) {
            sql1 = "insert into citiesairtrip (id_cities_citiesairtrip, id_airTrip_citiesairtrip) values ";
            for (City c : airTripIn.getCitiesVisited()) {
                sql1 = sql1.concat(String.format("(%s,%s),", c.getId(), airTripIn.getId()));
            }
            sql1 = sql1.substring(0, sql1.length() - 1).concat(";");

            ps = Connect.getconnectionAdmin().prepareStatement(sql1);
            resul = ps.executeUpdate() > 0;
        } else {
            resul = false;
        }

        //endregion ACTIONS


        // OUT
        return resul;

    }

    /**
     * Method to add flights of air trip on ddbb.
     *
     * @param airTripIn AirTrip classe, with flights list to add.
     * @return False = doesn't add; true = added correctly.
     * @throws SQLException Throws if some problem with SQL connection.
     */
    public boolean addAirTripFlights(AirTrip airTripIn) throws SQLException {
        //region DEFINITION VARIABLES
        boolean resul;
        String sql1;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        // PREPARE & EXECUTE QUERY
        sql1 = "insert into flightsairtrip (id_airTrip_flightsairtrip, id_flights_flightsairtrip) values ";
        for (Flight F : airTripIn.getFlights()) {
            sql1 = sql1.concat(String.format("(%s,%s),", airTripIn.getId(), F.getId()));
        }
        sql1 = sql1.substring(0, sql1.length() - 1).concat(";");

        ps = Connect.getconnectionAdmin().prepareStatement(sql1);
        resul = ps.executeUpdate() > 0;

        //endregion ACTIONS


        // OUT
        return resul;

    }

    //endregion METHODS: ADD


    //region METHODS: CHECK

    /**
     * Method to check if the city name or part city name, exit on the cities table.
     *
     * @param cityNameIn City name or part.
     * @param complet true = the city's name is complet; false = it's a part of city's name.
     * @return 0 = don't exist; 0> = exist and that is id number.
     * @throws SQLException Throws if some problem with SQL connection.
     */
    public int checkCityExist(String cityNameIn, boolean complet) throws SQLException {
        //region DEFINITION VARIABLES
        int resul = 0;
        String sql1;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        // INIT
        cityNameIn = cityNameIn.toUpperCase();

        // CHECKS
        if (!cityNameIn.isEmpty()) {
            // PREPARE & EXECUTE QUERY
            if (complet) {
                sql1 = "select * from cities where upper(name_cities) like \"" + cityNameIn + "\"";
            } else {
                sql1 = "select * from cities where upper(name_cities) like \"%" + cityNameIn + "%\"";
            }
            ps = Connect.getConnectionBasic().prepareStatement(sql1);
            rs = ps.executeQuery();

            // READ RESULTS
            while (rs.next()) {
                resul = rs.getShort("Id_cities");
            }
        }

        //endregion ACTIONS


        // OUT
        return resul;

    }

    /**
     * Method to check if the trip's name already exist.
     *
     * @param tripNameIn Trip name to check.
     * @return false = doesn't exist; 1 = exist.
     * @throws SQLException Throws if some problem with SQL connection.
     */
    public boolean checkTripExist(String tripNameIn) throws SQLException {
        //region DEFINITION VARIABLES
        boolean resul = false;
        String sql1;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        // INIT
        tripNameIn = tripNameIn.toUpperCase();

        // CHECKS
        if (!tripNameIn.isEmpty()) {

            // PREPARE & EXECUTE QUERY
            // Check if exist in airTip table
            sql1 = "select * from airtrip where upper(name_airTrip) like \"" + tripNameIn + "\"";
            ps = Connect.getConnectionBasic().prepareStatement(sql1);
            rs = ps.executeQuery();

            // READ RESULTS
            while (rs.next()) {
                resul = true;
            }

            // Check if exist in landTrip Table
            if (!resul) {
                sql1 = "select * from landtrip where upper(name_landTrip) like \"" + tripNameIn + "\"";
                ps = Connect.getConnectionBasic().prepareStatement(sql1);
                rs = ps.executeQuery();

                // READ RESULTS
                while (rs.next()) {
                    resul = true;
                }
            }
        }

        //endregion ACTIONS


        // OUT
        return resul;

    }

    /**
     * Method to check if the hotel's name already exist.
     *
     * @param hotelNameIn Hotel's name to check.
     * @return false = doesn't exist; 1 = exist.
     * @throws SQLException Throws if some problem with SQL connection.
     */
    public boolean checkHotelExist(String hotelNameIn) throws SQLException {
        //region DEFINITION VARIABLES
        boolean resul = false;
        String sql1;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        // INIT
        hotelNameIn = hotelNameIn.toUpperCase();

        // CHECKS
        if (!hotelNameIn.isEmpty()) {
            // PREPARE & EXECUTE QUERY
            // Check if exist in airTip table
            sql1 = "select * from hotels where upper(name_hotels) like \"" + hotelNameIn + "\"";
            ps = Connect.getConnectionBasic().prepareStatement(sql1);
            rs = ps.executeQuery();

            // READ RESULTS
            while (rs.next()) {
                resul = true;
            }
        }

        //endregion ACTIONS


        // OUT
        return resul;

    }

    //endregion METHODS: CHECK


    //region METHODS: GETS

    /**
     * Method to get a city find with city's name.
     *
     * @param cityNameIn Name to city for search.
     * @return City classe found.
     * @throws SQLException Throws if some problem with SQL connection.
     */
    public City getCity(String cityNameIn) throws SQLException {
        //region DEFINITION VARIABLES
        String sql1;
        City cityResul = null;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        // CHECKS
        if (!cityNameIn.isEmpty()) {
            // PREPARE & EXECUTE QUERY
            sql1 = "select * from cities where name_cities like \"" + cityNameIn + "\"";
            ps = Connect.getConnectionBasic().prepareStatement(sql1);
            rs = ps.executeQuery();

            // READ RESULTS
            while (rs.next()) {
                cityResul = new City(
                        rs.getShort("Id_cities"),
                        rs.getString("name_cities")
                );
            }
        }

        //endregion ACTIONS


        // OUT
        return cityResul;

    }

    /**
     * Method to get a AirTrip find with AirTrip's name or AirTrip's ID.
     *
     * @param airTripIn Classe of AirTrip.
     * @return AirTrip classe found.
     * @throws SQLException Throws if some problem with SQL connection.
     */
    public AirTrip getAirTrip(AirTrip airTripIn) throws SQLException {
        //region DEFINITION VARIABLES
        String sql1 = "";
        AirTrip airTripResul = null;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        // CHECKS
        // PREPARE & EXECUTE QUERY
        if (!airTripIn.getName().isEmpty()) {
            sql1 = "select * from airtrip where name_airTrip like \"" + airTripIn.getName() + "\"";
        } else if (airTripIn.getId() > 0) {
            sql1 = "select * from airtrip where name_airTrip = " + airTripIn.getId();
        }
        ps = Connect.getConnectionBasic().prepareStatement(sql1);
        rs = ps.executeQuery();

        // READ RESULTS
        while (rs.next()) {
            airTripResul = new AirTrip(
                    rs.getShort("Id_airTrip"),
                    rs.getString("name_airTrip"),
                    rs.getShort("days_airTrip"),
                    new ArrayList<>(),
                    new ArrayList<>()
            );
        }

        //endregion ACTIONS


        // OUT
        return airTripResul;

    }


    /**
     * Method to get a LandTrip find with LandTrip's name or LandTrip's ID.
     *
     * @param landTripIn Classe of LandTrip.
     * @return LandTrip classe found.
     * @throws SQLException Throws if some problem with SQL connection.
     */
    public LandTrip getLandTrip(LandTrip landTripIn) throws SQLException {
        //region DEFINITION VARIABLES
        String sql1 = "";
        LandTrip landTrip = null;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        // CHECKS
        // PREPARE & EXECUTE QUERY
        if (!landTripIn.getName().isEmpty()) {
            sql1 = "select * from landtrip where name_landTrip like \"" + landTripIn.getName() + "\"";
        } else if (landTripIn.getId() > 0) {
            sql1 = "select * from landtrip where days_landTrip = " + landTripIn.getId();
        }
        ps = Connect.getConnectionBasic().prepareStatement(sql1);
        rs = ps.executeQuery();

        // READ RESULTS
        while (rs.next()) {
            landTrip = new LandTrip(
                    rs.getShort("Id_landTrip"),
                    rs.getString("name_landTrip"),
                    rs.getShort("days_landTrip"),
                    new ArrayList<>(),
                    new ArrayList<>()
            );
        }

        //endregion ACTIONS


        // OUT
        return landTrip;

    }

    //endregion METHODS: GETS

}
