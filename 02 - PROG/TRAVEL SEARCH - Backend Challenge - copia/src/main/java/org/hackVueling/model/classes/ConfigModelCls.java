package org.hackVueling.model.classes;

import org.hackVueling.model.dataStructrure.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.stream.Collector;

public class ConfigModelCls {
    //region ATTRIBUTES
    private static ConfigModelCls instance;
    private static PreparedStatement ps;
    private static ResultSet rs;
    private Connect connector;

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


    //region GETTERS & SETTERS

    //endregion GETTERS & SETTERS


    //region METHODS: ADD
    public boolean addAirTrip(AirTrip airTripIn) throws SQLException {
        //region DEFINITION VARIABLES
        boolean resul = false;
        String sql1 = "";

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        // INIT
        connector = new Connect();

        // PREPARE & EXECUTE QUERY
        sql1 = "insert into airtrip (name_airTrip, days_airTrip) values (?, ?)";
        ps = connector.connectionAdmin().prepareStatement(sql1);

        ps.setString(1, airTripIn.getName());
        ps.setShort(2, airTripIn.getTripDays());
        resul = ps.executeUpdate() > 0;

        //endregion ACTIONS


        // OUT
        return resul;

        //endregion METHODS
    }

    public boolean addCity(City city) throws SQLException {
        //region DEFINITION VARIABLES
        boolean resul = false;
        String sql1 = "";

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        // INIT
        connector = new Connect();

        // PREPARE & EXECUTE QUERY
        sql1 = "insert into cities (name_cities) values (?)";
        ps = connector.connectionAdmin().prepareStatement(sql1);
        ps.setString(1, city.getName());
        resul = ps.executeUpdate() > 0;

        //endregion ACTIONS


        // OUT
        return resul;

    }

    public boolean addFLight(Flight flightIn) throws SQLException {
        //region DEFINITION VARIABLES
        boolean resul = false;
        String sql1 = "";

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        // INIT
        connector = new Connect();

        // PREPARE & EXECUTE QUERY
        sql1 = "insert into flights (Id_cities_flights, departureTime_flights) values (?, ?)";
        ps = connector.connectionAdmin().prepareStatement(sql1);

        ps.setShort(1, flightIn.getIdCity());
        ps.setTime(2, flightIn.getDepartureTime());
        resul = ps.executeUpdate() > 0;

        //endregion ACTIONS


        // OUT
        return resul;

        //endregion METHODS
    }

    public boolean addHotel(Hotel hotelIn) throws SQLException {
        //region DEFINITION VARIABLES
        boolean resul = false;
        String sql1 = "";

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        // INIT
        connector = new Connect();

        // PREPARE & EXECUTE QUERY
        sql1 = "insert into hotels (Id_cities_hotels, name_hotels, idCategory_hotels) values (?, ?, ?)";
        ps = connector.connectionAdmin().prepareStatement(sql1);
        ps.setShort(1, hotelIn.getIdCity());
        ps.setString(2, hotelIn.getName());
        ps.setByte(3, hotelIn.getCategory());
        resul = ps.executeUpdate() > 0;

        //endregion ACTIONS


        // OUT
        return resul;

    }

    public boolean addLandTrip(LandTrip landTripIn) throws SQLException {
        //region DEFINITION VARIABLES
        boolean resul = false;
        String sql1 = "";

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        // INIT
        connector = new Connect();

        // PREPARE & EXECUTE QUERY
        sql1 = "insert into landtrip (name_landTrip, days_landTrip) values (?, ?)";
        ps = connector.connectionAdmin().prepareStatement(sql1);

        ps.setString(1, landTripIn.getName());
        ps.setShort(2, landTripIn.getTripDays());
        resul = ps.executeUpdate() > 0;

        //endregion ACTIONS


        // OUT
        return resul;

        //endregion METHODS
    }

    public boolean addLandTripCities(LandTrip landTripIn) throws SQLException {
        //region DEFINITION VARIABLES
        boolean resul = false;
        String sql1 = "";

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        // INIT
        connector = new Connect();

        // PREPARE & EXECUTE QUERY
        sql1 = "insert into citieslandtrip (id_landTrip_citieslandtrip, id_cities_citieslandtrip) values ";
        for (City c : landTripIn.getCitiesVisited()) {
            sql1 = sql1.concat(String.format("(%s,%s),", landTripIn.getId(), c.getId()));
        }
        sql1 = sql1.substring(0, sql1.length() - 1).concat(";");

        ps = connector.connectionAdmin().prepareStatement(sql1);
        resul = ps.executeUpdate() > 0;

        //endregion ACTIONS


        // OUT
        return resul;

        //endregion METHODS
    }

    public boolean addLandTripHotels(LandTrip landTripIn) throws SQLException {
        //region DEFINITION VARIABLES
        boolean resul = false;
        String sql1 = "";

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        // INIT
        connector = new Connect();

        // PREPARE & EXECUTE QUERY
        sql1 = "insert into hotelslandtrip (id_hotels_hotelslandtrip, id_landTrip_hotelslandtrip) values ";
        for (Hotel h : landTripIn.getHotels()) {
            sql1 = sql1.concat(String.format("(%s,%s),", h.getId(), landTripIn.getId()));
        }
        sql1 = sql1.substring(0, sql1.length() - 1).concat(";");

        ps = connector.connectionAdmin().prepareStatement(sql1);
        resul = ps.executeUpdate() > 0;

        //endregion ACTIONS


        // OUT
        return resul;

        //endregion METHODS
    }

    public boolean addAirTripCities(AirTrip airTripIn) throws SQLException {
        //region DEFINITION VARIABLES
        boolean resul = false;
        String sql1 = "";

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        // INIT
        connector = new Connect();

        // PREPARE & EXECUTE QUERY
        sql1 = "insert into citiesairtrip (id_cities_citiesairtrip, id_airTrip_citiesairtrip) values ";
        for (City c : airTripIn.getCitiesVisited()) {
            sql1 = sql1.concat(String.format("(%s,%s),", c.getId(), airTripIn.getId()));
        }
        sql1 = sql1.substring(0, sql1.length() - 1).concat(";");

        ps = connector.connectionAdmin().prepareStatement(sql1);
        resul = ps.executeUpdate() > 0;

        //endregion ACTIONS


        // OUT
        return resul;

        //endregion METHODS
    }

    public boolean addAirTripFlights(AirTrip airTripIn) throws SQLException {
        //region DEFINITION VARIABLES
        boolean resul = false;
        String sql1 = "";

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        // INIT
        connector = new Connect();

        // PREPARE & EXECUTE QUERY
        sql1 = "insert into flightsairtrip (id_airTrip_flightsairtrip, id_flights_flightsairtrip) values ";
        for (Flight F : airTripIn.getFlights()) {
            sql1 = sql1.concat(String.format("(%s,%s),", airTripIn.getId(), F.getId()));
        }
        sql1 = sql1.substring(0, sql1.length() - 1).concat(";");

        ps = connector.connectionAdmin().prepareStatement(sql1);
        resul = ps.executeUpdate() > 0;

        //endregion ACTIONS


        // OUT
        return resul;

        //endregion METHODS
    }

    //endregion METHODS: ADD


    //region METHODS: CHECK

    /**
     * Method to check if the city name or part city name, exit on the cities table.
     *
     * @param cityNameIn City name or part.
     * @return 0 = don't exist; 0> = exist and that is id number.
     * @throws SQLException
     */
    public int checkCityExist(String cityNameIn, boolean complet) throws SQLException {
        //region DEFINITION VARIABLES
        int resul = 0;
        String sql1;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        // INIT
        connector = new Connect();
        cityNameIn = cityNameIn.toUpperCase();
        // Cheks
        if (!cityNameIn.isEmpty()) {
            // PREPARE & EXECUTE QUERY
            if (complet) {
                sql1 = "select * from cities where upper(name_cities) like \"" + cityNameIn + "\"";
            } else {
                sql1 = "select * from cities where upper(name_cities) like \"%" + cityNameIn + "%\"";
            }
            ps = connector.connectionBasic().prepareStatement(sql1);
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


    public boolean checkTripExist(String tripNameIn) throws SQLException {
        //region DEFINITION VARIABLES
        boolean resul = false;
        String sql1 = "";

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        // INIT
        connector = new Connect();
        tripNameIn = tripNameIn.toUpperCase();
        // Cheks
        if (!tripNameIn.isEmpty()) {

            // PREPARE & EXECUTE QUERY
            // Check if exist in airTip table
            sql1 = "select * from airtrip where upper(name_airTrip) like \"" + tripNameIn + "\"";
            ps = connector.connectionBasic().prepareStatement(sql1);
            rs = ps.executeQuery();

            // READ RESULTS
            while (rs.next()) {
                resul = true;
            }

            // Check if exist in landTrip Table
            if (!resul) {
                sql1 = "select * from landtrip where upper(name_landTrip) like \"" + tripNameIn + "\"";
                ps = connector.connectionBasic().prepareStatement(sql1);
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

    public boolean checkHotelExist(String hotelNameIn) throws SQLException {
        //region DEFINITION VARIABLES
        boolean resul = false;
        String sql1 = "";

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        // INIT
        connector = new Connect();
        hotelNameIn = hotelNameIn.toUpperCase();
        // Cheks
        if (!hotelNameIn.isEmpty()) {
            // PREPARE & EXECUTE QUERY
            // Check if exist in airTip table
            sql1 = "select * from hotels where upper(name_hotels) like \"" + hotelNameIn + "\"";
            ps = connector.connectionBasic().prepareStatement(sql1);
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


}
