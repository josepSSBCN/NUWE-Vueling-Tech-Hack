package org.hackVueling.model.classes;

import org.hackVueling.ddbb_simul.DdbbService;
import org.hackVueling.model.dataStructrure.*;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class SearchModelCls {
    //region ATTRIBUTES
    private static SearchModelCls instance;
    private static PreparedStatement ps;
    private static ResultSet rs;
    private Connect connector;


    //endregion ATTRIBUTES


    //region CONSTRUCTOR
    private SearchModelCls() {

    }

    public static SearchModelCls getInstance() {
        if (instance == null) {
            instance = new SearchModelCls();
        }
        return instance;
    }

    //endregion CONSTRUCTOR


    //region GETTERS & SETTERS
    // todo clean if isn't necessary
    //endregion GETTERS & SETTERS


    //region METHODS

    public List<Short>getAirTripsWitCity(String cityNameIn) throws SQLException {
        //region DEFINITION VARIABLES
        String sql1 = "";
        List<Short> tripsIdList = new ArrayList<>();

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        // INIT
        connector = new Connect();
        cityNameIn = cityNameIn.toUpperCase();
        // Cheks
        if (cityNameIn.isEmpty()){
            return tripsIdList;
        }

        //region FIND ON `air_trip_cities_vw`
        // Prepare & execute the query
        sql1 = "select id_airTrip_citiesairtrip from air_trip_cities_vw where upper(name_cities) like \"%" + cityNameIn + "%\"";
        ps = connector.connectionBasic().prepareStatement(sql1);
        rs = ps.executeQuery();

        // Read results
        while (rs.next()){
            tripsIdList.add(rs.getShort("id_airTrip_citiesairtrip"));
        }

        //endregion FIND ON `air_trip_cities_vw`

        //region FIND ON `air_trip_flights_vw`
        // Prepare & execute the query
        sql1 = "select id_airTrip_flightsairtrip from air_trip_flights_vw where upper(name_cities) like \"%" + cityNameIn + "%\"";
        ps = connector.connectionBasic().prepareStatement(sql1);
        rs = ps.executeQuery();

        // Read results
        while (rs.next()){
            tripsIdList.add(rs.getShort("id_airTrip_flightsairtrip"));
        }

        //endregion FIND ON `air_trip_flights_vw`

        //endregion ACTIONS


        // OUT
        return tripsIdList;

    }

    public List<Short>getLandTripsWitCity(String cityNameIn) throws SQLException {
        //region DEFINITION VARIABLES
        String sql1 = "";
        List<Short> tripsIdList = new ArrayList<>();

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        // INIT
        connector = new Connect();
        cityNameIn = cityNameIn.toUpperCase();
        // Cheks
        if (cityNameIn.isEmpty()){
            return tripsIdList;
        }

        //region FIND ON `land_trip_cities_vw`
        // Prepare & execute the query
        sql1 = "select id_landTrip_citieslandtrip from land_trip_cities_vw where upper(name_cities) like \"%" + cityNameIn + "%\"";
        ps = connector.connectionBasic().prepareStatement(sql1);
        rs = ps.executeQuery();

        // Read results
        while (rs.next()){
            tripsIdList.add(rs.getShort("id_landTrip_citieslandtrip"));
        }

        //endregion FIND ON `land_trip_cities_vw`

        //region FIND ON `land_trip_hotels_vw`
        // Prepare & execute the query
        sql1 = "select id_landTrip_hotelslandtrip from land_trip_hotels_vw where upper(name_cities) like \"%" + cityNameIn + "%\"";
        ps = connector.connectionBasic().prepareStatement(sql1);
        rs = ps.executeQuery();

        // Read results
        while (rs.next()){
            tripsIdList.add(rs.getShort("id_landTrip_hotelslandtrip"));
        }

        //endregion FIND ON `land_trip_hotels_vw`

        //endregion ACTIONS


        // OUT
        return tripsIdList;

    }

    public List<AirTrip> getAirTripOfIdList(List<Short> airTripsIdList) throws SQLException {
        //region DEFINITION VARIABLES
        String sql1 = "", sql11 = "", sql21 = "", sql31 = "";
        List<AirTrip> airTripList = new ArrayList<>();

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        // INIT
        connector = new Connect();

        // PREPARE 'WHERE' PART OF QUERYS
        for (int i : airTripsIdList) {
            sql11 = sql11.concat(String.format("Id_airTrip = %s OR ", i));
            sql21 = sql21.concat(String.format("id_airTrip_flightsairtrip = %s OR ", i));
            sql31 = sql31.concat(String.format("id_airTrip_citiesairtrip = %s OR ", i));
        }
        sql11 = sql11.substring(0, sql11.length() - 4);
        sql21 = sql21.substring(0, sql21.length() - 4);
        sql31 = sql31.substring(0, sql31.length() - 4);

        //region airTrip
        // PREPARE & EXECUTE QUERY
        sql1 = "select * from airtrip where " + sql11;
        ps = connector.connectionBasic().prepareStatement(sql1);
        rs = ps.executeQuery();

        // READ LANDTRIP TABLE RESULTS
        while (rs.next()) {
            AirTrip airTrip = new AirTrip(
                    rs.getShort("Id_airTrip"),
                    rs.getString("name_airTrip"),
                    rs.getShort("days_airTrip"),
                    new ArrayList<>(),
                    new ArrayList<>()
            );

            airTripList.add(airTrip);
        }

        //endregion airTrip

        //region air_trip_flights_vw VIEW
        // PREPARE & EXECUTE QUERY
        sql1 = "select * from air_trip_flights_vw where " + sql21;
        ps = connector.connectionBasic().prepareStatement(sql1);
        rs = ps.executeQuery();

        // READ RESULTS
        while (rs.next()) {
            short idAirTrip = rs.getShort("id_airTrip_flightsairtrip");
            Flight flight = new Flight(
                    rs.getShort("Id_flights"),
                    rs.getShort("Id_cities"),
                    rs.getString("name_cities"),
                    rs.getTime("departureTime_flights")
            );

            List<AirTrip> tempAT = airTripList.stream().filter(x -> x.getId() == idAirTrip).toList();
            if (tempAT.size()>0) airTripList.get(airTripList.indexOf(tempAT.get(0))).addFlight(flight);

        }

        //endregion air_trip_flights_vw

        //region air_trip_cities_vw
        // Prepare & execute the query
        sql1 = "select * from air_trip_cities_vw where " + sql31;
        sql1 = sql1.substring(0, sql1.length() - 4);
        ps = connector.connectionBasic().prepareStatement(sql1);
        rs = ps.executeQuery();

        // READ RESULTS
        while (rs.next()){
            short idAirTrip = rs.getShort("id_airTrip_citiesairtrip");
            City city = new City(
                    rs.getShort("Id_cities"),
                    rs.getString("name_cities")
            );

            List<AirTrip> tempAT = airTripList.stream().filter(x -> x.getId() == idAirTrip).toList();
            if (tempAT.size()>0) airTripList.get(airTripList.indexOf(tempAT.get(0))).addCity(city);

        }

        //endregion land_trip_cities_vw



        //endregion ACTIONS


        // OUT
        return airTripList;

    }

    public List<LandTrip> getLandTripOfIdList(List<Short> landTripsIdList) throws SQLException {
        //region DEFINITION VARIABLES
        boolean resul = false;
        String sql1 = "", sql11 = "", sql21 = "", sql31 = "";
        List<LandTrip> landTripList = new ArrayList<>();

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        // INIT
        connector = new Connect();

        // PREPARE WHERE PART OF QUERYS
        for (int i : landTripsIdList) {
            sql11 = sql11.concat(String.format("Id_landTrip = %s OR ", i));
            sql21 = sql21.concat(String.format("id_landTrip_hotelslandtrip = %s OR ", i));
            sql31 = sql31.concat(String.format("id_landTrip_citieslandtrip = %s OR ", i));
        }
        sql11 = sql11.substring(0, sql11.length() - 4);
        sql21 = sql21.substring(0, sql21.length() - 4);
        sql31 = sql31.substring(0, sql31.length() - 4);

        //region landtrip
        // PREPARE & EXECUTE LANDTRIP TABLE QUERY
        sql1 = "select * from landtrip where " + sql11;
        ps = connector.connectionBasic().prepareStatement(sql1);
        rs = ps.executeQuery();

        // READ LANDTRIP TABLE RESULTS
        while (rs.next()) {
            LandTrip landTrip = new LandTrip(
                    rs.getShort("Id_landTrip"),
                    rs.getString("name_landTrip"),
                    rs.getShort("days_landTrip"),
                    new ArrayList<>(),
                    new ArrayList<>()
            );

            landTripList.add(landTrip);
        }

        //endregion landtrip

        //region land_trip_hotels_vw VIEW
        // PREPARE & EXECUTE QUERY
        sql1 = "select * from land_trip_hotels_vw where " + sql21;
        ps = connector.connectionBasic().prepareStatement(sql1);
        rs = ps.executeQuery();

        // READ RESULTS
        while (rs.next()) {
            short idLandTrip = rs.getShort("id_landTrip_hotelslandtrip");
            Hotel hotel = new Hotel(
                    rs.getShort("Id_hotels"),
                    rs.getString("name_hotels"),
                    rs.getShort("Id_cities"),
                    rs.getString("name_cities"),
                    rs.getByte("idCategory_hotels")
            );

            List<LandTrip> tempLT = landTripList.stream().filter(x -> x.getId() == idLandTrip).toList();
            if (tempLT.size()>0) landTripList.get(landTripList.indexOf(tempLT.get(0))).addHotel(hotel);

        }

        //endregion land_trip_hotels_vw

        //region land_trip_cities_vw
        // PREPARE & EXECUTE VIEW QUERY
        sql1 = "select * from land_trip_cities_vw where " + sql31;
        sql1 = sql1.substring(0, sql1.length() - 4);
        ps = connector.connectionBasic().prepareStatement(sql1);
        rs = ps.executeQuery();

        // READ RESULTS
        while (rs.next()){
            short idLandTrip = rs.getShort("id_landTrip_citieslandtrip");
            City city = new City(
                    rs.getShort("Id_cities"),
                    rs.getString("name_cities")
            );

            List<LandTrip> tempLT = landTripList.stream().filter(x -> x.getId() == idLandTrip).toList();
            if (tempLT.size()>0) landTripList.get(landTripList.indexOf(tempLT.get(0))).addCity(city);

        }

        //endregion land_trip_cities_vw

        //endregion ACTIONS


        // OUT
        return landTripList;

    }

    /**
     * Method to check if the city name or part city name, exit on the cities table.
     *
     * @param cityNameIn City name or part.
     * @return false = don't exist; true = exist.
     * @throws SQLException
     */
    public boolean checkCityExist(String cityNameIn) throws SQLException {
        //region DEFINITION VARIABLES
        boolean resul = false;
        String sql1 = "";

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        // INIT
        connector = new Connect();
        cityNameIn = cityNameIn.toUpperCase();
        // Cheks
        if (cityNameIn.isEmpty()){
            return resul;
        }

        // PREPARE & EXECUTE QUERY
        sql1 = "select * from cities where upper(name_cities) like \"%" + cityNameIn + "%\"";
        ps = connector.connectionBasic().prepareStatement(sql1);
        rs = ps.executeQuery();

        // READ RESULTS
        while (rs.next()) {
            resul = true;
        }

        //endregion ACTIONS


        // OUT
        return resul;

    }

    //endregion METHODS

}
