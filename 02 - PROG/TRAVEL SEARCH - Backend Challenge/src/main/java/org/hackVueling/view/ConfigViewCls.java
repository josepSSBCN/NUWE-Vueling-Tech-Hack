package org.hackVueling.view;

import org.hackVueling.control.ConfigControlCls;
import org.hackVueling.model.dataStructrure.*;
import org.hackVueling.model.enums.EnumCategorys;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe to control all actions of Configuration on View layer.
 */
public class ConfigViewCls {
    //region ATTRIBUTES
    private static ConfigViewCls instance;
    private static ConfigControlCls configControlCls;

    //endregion ATTRIBUTES


    //region CONSTRUCTOR
    private ConfigViewCls() {
        configControlCls = ConfigControlCls.getInstance();
    }

    public static ConfigViewCls getInstance() {
        if (instance == null) {
            instance = new ConfigViewCls();
        }
        return instance;
    }

    //endregion CONSTRUCTOR


    //region METHODS

    /**
     * Method to show and control screen "Configuration Menu".
     */
    public void ConfigMenu() {
        //region DEFINITION VARIABLES
        boolean exit = false;
        int optionSelected;
        String text;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try {
            do {
                // TITLE
                text = ToolsViewCls.title(ToolsViewCls.getConfigMenu());

                // BODY
                text = String.format(ToolsViewCls.getLangText(300), text);

                // USER DATA IN
                System.out.println(text);
                optionSelected = ToolsViewCls.getInt(4, 0, "", "");

                // SELECTION MANAGE
                switch (optionSelected) {
                    case 0 -> exit = true;
                    case 1 -> AddCity();
                    case 2 -> AddFlight();
                    case 3 -> AddHotel();
                    case 4 -> AddTrip();
                    default -> System.out.println(ToolsViewCls.getLangText(2001));
                }

            } while (!exit);
        } catch (Exception ex) {
            ToolsViewCls.pressAnyKey(ToolsViewCls.getLangText(2000));
        }
        //endregion ACTIONS

    }

    //endregion METHODS


    //region METHODS: ADD

    /**
     * Method to show and control screen 'Add city'
     */
    private void AddCity() {
        //region DEFINITION VARIABLES
        boolean exitDo = false;
        int resulAddMet;
        String text = "", cityName, messageCity;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try {
            // TITLE
            System.out.println(ToolsViewCls.title(ToolsViewCls.getAddCity()) + "\n");

            // USER INSERT DATA
            messageCity = ToolsViewCls.getLangText(301);
            do {
                cityName = ToolsViewCls.getString(messageCity);

                if (!cityName.equals("0")) {
                    // Add city
                    resulAddMet = configControlCls.addCity(cityName);
                    switch (resulAddMet) {
                        case 0 -> {         // Added ok
                            text = String.format(ToolsViewCls.getLangText(302), cityName);
                            exitDo = true;
                        }
                        case 1, 3 -> {      // Error on saving
                            text = ToolsViewCls.getLangText(303);
                            exitDo = true;
                        }
                        case 2 -> messageCity = ToolsViewCls.getLangText(304);      // This city Exist
                        case 4 -> messageCity = ToolsViewCls.getLangText(305);      // Name empty
                    }
                } else {
                    exitDo = true;
                }

            } while (!exitDo);

        } catch (Exception ex) {
            text = ToolsViewCls.getLangText(2000);
        }

        //endregion ACTIONS


        // OUT
        ToolsViewCls.pressAnyKey(text + "\n");

    }

    /**
     * Method to add a new flight on ddbb. This method ask step by step, all info it needed.
     */
    private void AddFlight() {
        //region DEFINITION VARIABLES
        boolean exitMethod = false;
        int step = 0;
        String text = "", cityName = "", departureTime = "";

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try {
            // TITLE
            System.out.println(ToolsViewCls.title(ToolsViewCls.getAddFlight()) + "\n");

            do {
                switch (step) {
                    case 0 -> {         // Insert departure city's name
                        cityName = ToolsViewCls.getString(ToolsViewCls.getLangText(310));
                        exitMethod = cityName.equals("0");
                        step = 1;
                    }
                    case 1 -> {         // Insert departure time
                        departureTime = ToolsViewCls.getDepartureTime();
                        exitMethod = departureTime.equals("0");
                        step = 2;
                    }
                    case 2 -> {         // Add new flight on ddbb
                        text = addFlight_New(cityName, departureTime);
                        exitMethod = text.length() > 0;
                        step = 0;
                    }
                    default -> text = ToolsViewCls.getLangText(2001);
                }
            } while (!exitMethod);

        } catch (Exception ex) {
            text = ToolsViewCls.getLangText(2000);
        }

        //endregion ACTIONS


        // OUT
        ToolsViewCls.pressAnyKey(text + "\n");

    }

    /**
     * Method to add a new hotel on ddbb. This method ask step by step, all info it needed.
     */
    private void AddHotel() {
        //region DEFINITION VARIABLES
        boolean exitMethod;
        int step = 0;
        String message = "";
        Hotel newHotel;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try {
            // INIT
            newHotel = new Hotel();

            // TITLE
            System.out.println(ToolsViewCls.title(ToolsViewCls.getAddHotel()) + "\n");

            do {
                switch (step) {
                    case 0 -> {     // Insert hotel's name
                        exitMethod = addHotel_InsertHotelName(newHotel);
                        step = 1;
                    }
                    case 1 -> {     // Select hotel's category
                        exitMethod = addHotel_SelectCategory(newHotel);
                        step = 2;
                    }
                    case 2 -> {     // Insert hotel's city.
                        exitMethod = addHotel_InsertCity(newHotel);
                        step = 3;
                    }
                    case 3 -> {     // Add new hotel
                        message = addHotel_New(newHotel);
                        exitMethod = true;
                        step = 4;
                    }
                    default -> {
                        message = ToolsViewCls.getLangText(2001);
                        exitMethod = true;
                    }
                }

            } while (!exitMethod);


        } catch (Exception ex) {
            System.out.println(ToolsViewCls.getLangText(2001));
        }

        //endregion ACTIONS

        // OUT
        ToolsViewCls.pressAnyKey(message + "\n");

    }

    /**
     * Method to add a new trip on ddbb. This method ask step by step, all info it needed.
     */
    private void AddTrip() {
        //region DEFINITION VARIABLES

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try {
            // TITLE
            System.out.println(ToolsViewCls.title(ToolsViewCls.getAddTrip()) + "\n");

            switch (addTrips_SelectType()) {
                case 1 -> addTrip_AirTrip();
                case 2 -> addTrip_LandTrip();
            }
        } catch (Exception ex) {
            ToolsViewCls.pressAnyKey(ToolsViewCls.getLangText(2000));
        }

        //endregion ACTIONS

    }

    //endregion METHODS: ADD


    //region METHOD: PRIVATES OF ADD FLIGHT
    private String addFlight_New(String cityNameIn, String departureTimeIn) {
        //region DEFINITION VARIABLES
        int resulAddMet;
        String resul = "";

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        resulAddMet = configControlCls.addFlight(cityNameIn, departureTimeIn);
        switch (resulAddMet) {
            case 0 -> resul = ToolsViewCls.getLangText(311);                // Added OK
            case 1, 3 -> resul = ToolsViewCls.getLangText(312);             // Error during save
            case 2 -> System.out.println(ToolsViewCls.getLangText(313));    // City doesn't exist
        }

        //endregion ACTIONS


        // OUT
        return resul;

    }

    //endregion METHOD: PRIVATES OF ADD FLIGHT


    //region METHOD: PRIVATES OF ADD HOTEL
    private boolean addHotel_InsertHotelName(Hotel newHotelIn) {
        //region DEFINITION VARIABLES
        boolean resul = false, exitDo = false;
        int resulCheckHotel;
        String message = "";

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        do {
            message = message + ToolsViewCls.getLangText(320);
            newHotelIn.setName(ToolsViewCls.getString(message));

            // Check if exist
            resulCheckHotel = (newHotelIn.getName().equals("0")) ? 1000 : configControlCls.checkHotel(newHotelIn.getName());

            switch (resulCheckHotel) {
                case 0 -> {
                    message = "";
                    exitDo = true;
                }
                case 1 -> {
                    message = ToolsViewCls.getLangText(321);
                    exitDo = true;
                    resul = true;
                }
                case 2 -> message = ToolsViewCls.getLangText(326);
                case 1000 -> {
                    message = "";
                    exitDo = true;
                    resul = true;
                }
                default -> {
                }
            }
        } while (!exitDo);
        if (message.length() > 0) System.out.println(message);

        //endregion ACTIONS


        // OUT
        return resul;

    }

    private boolean addHotel_SelectCategory(Hotel newHotelIn) {
        //region DEFINITION VARIABLES
        boolean resul;
        int idCategory;
        String message;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        message = String.format("\n1.- %s\n2.- %s\n3.- %s\n4.- %s\n5.- %s\n6.- %s\n0.- %s",
                EnumCategorys.One.name, EnumCategorys.Two.name, EnumCategorys.Three.name,
                EnumCategorys.Fort.name, EnumCategorys.Five.name, EnumCategorys.Six.name,
                ToolsViewCls.getLangText(322));
        idCategory = ToolsViewCls.getInt(6, 0, message, "");
        newHotelIn.setIdCategory((byte) idCategory);
        resul = idCategory == 0;
        //endregion ACTIONS


        // OUT
        return resul;

    }

    private boolean addHotel_InsertCity(Hotel newHotelIn) {
        //region DEFINITION VARIABLES
        boolean resul = false, exitDo;
        String message, cityName;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        message = ToolsViewCls.getLangText(323);
        exitDo = false;

        do {
            cityName = ToolsViewCls.getString(message);

            // Checks
            if (cityName.equals("0")) {
                message = "";
                exitDo = true;
                resul = true;
            } else if (configControlCls.checkCity(cityName) == 2) {
                message = ToolsViewCls.getLangText(324);
            } else {
                newHotelIn.setCityName(cityName);
                exitDo = true;
            }
        } while (!exitDo);

        //endregion ACTIONS


        // OUT
        return resul;

    }

    private String addHotel_New(Hotel newHotelIn) {
        //region DEFINITION VARIABLES
        int resulAdded, messageId;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        resulAdded = configControlCls.addHotel(newHotelIn.getName(), newHotelIn.getCityName(), newHotelIn.getIdCategory());
        messageId = (resulAdded == 0) ? 325 : 2002;

        //endregion ACTIONS


        // OUT
        return ToolsViewCls.getLangText(messageId);

    }

    //endregion METHOD: PRIVATE


    //region METHOD: PRIVATES OF ADD AIR TRIPS

    /**
     * Method to select what type of trip we want to create.
     *
     * @return 1 = Air Trip Selected; 2 = Land Trip Selected; 0 = return;
     */
    private int addTrips_SelectType() {
        //region DEFINITION VARIABLES
        int tempInt, resul;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        System.out.println(ToolsViewCls.getLangText(332));

        tempInt = ToolsViewCls.getInt(3, 0, "", "");
        switch (tempInt) {
            case 1 -> resul = 1;
            case 2 -> resul = 2;
            default -> resul = 0;
        }

        //endregion ACTIONS


        // OUT
        return resul;

    }

    /**
     * Method to manage the Air Trip creation and saved it.
     */
    private void addTrip_AirTrip() {
        //region DEFINITION VARIABLES
        boolean exitDo = false;
        int step = 0;
        List<String> citiesList = new ArrayList<>();
        List<City> flightsList = new ArrayList<>();
        AirTrip newAirTrip = new AirTrip();

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        do {
            switch (step) {
                case 0 -> {         // Insert Trip Name
                    newAirTrip.setName(addTrip_InsertName());
                    exitDo = newAirTrip.getName().length() <= 0;
                    step = 1;
                }
                case 1 -> {         // Insert trip duration
                    newAirTrip.setTripDays(addTrip_InsertDays());
                    exitDo = newAirTrip.getTripDays() == 0;
                    step = 2;
                }
                case 2 -> {         // Insert citys
                    citiesList.addAll(addTrip_Cities());
                    exitDo = citiesList.size() <= 0;
                    step = 3;
                }
                case 3 ->         // Insert flights
                    ////*exitdo = addTrip_Flights(newAirTrip);
                    ////*ToolsViewCls.pressAnyKey("\n" + ToolsViewCls.getLangText(2001));
                    ////*exitdo = true;
                        step = 4;
                case 4 -> {         // Add new Air Trip
                    exitDo = addTrip_NewAir(newAirTrip, citiesList);
                    step = 6;
                }
                default -> exitDo = true;
            }

        } while (!exitDo);

        //endregion ACTIONS


    }

    /**
     * Method to ask the user to insert a trip name.
     *
     * @return Trip name.
     */
    public String addTrip_InsertName() {
        //region DEFINITION VARIABLES
        boolean exitDo = false;
        int resulCheckTrip;
        String message = "", tripName;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        do {
            message = message + ToolsViewCls.getLangText(330);
            tripName = ToolsViewCls.getString(message);

            // Check if exist
            resulCheckTrip = (tripName.equals("0")) ? 1000 : configControlCls.checkTrip(tripName);

            switch (resulCheckTrip) {
                case 0 -> {       // Trip's name, dosen't exit
                    message = "";
                    exitDo = true;
                }
                case 1 -> {     // Some error occurred in sub-method.
                    message = ToolsViewCls.getLangText(321);
                    exitDo = true;
                }
                case 2 -> message = ToolsViewCls.getLangText(331);      // Trip name already exist.
                case 1000 -> {       // Some error ocurred.
                    message = "";
                    exitDo = true;
                    tripName = "";
                }
                default -> ToolsViewCls.getLangText(2002);
            }
        } while (!exitDo);

        if (message.length() > 0) System.out.println(message);

        //endregion ACTIONS


        // OUT
        return tripName;

    }

    /**
     * Method to ask the user, trip days.
     *
     * @return 0 = Exit add Trip; > 0 =
     */
    public short addTrip_InsertDays() {
        //region DEFINITION VARIABLES
        short resul;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        resul = (short) ToolsViewCls.getInt(365, 0, ToolsViewCls.getLangText(333), "");

        //endregion ACTIONS


        // OUT
        return resul;

    }

    /**
     * Method to insert the cities what trip will visit.
     *
     * @return List of the city's.
     */
    public List<String> addTrip_Cities() {
        //region DEFINITION VARIABLES
        boolean exitDo;
        String message, cityName;
        List<String> citiesNames = new ArrayList<>();

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        message = ToolsViewCls.getLangText(334);
        exitDo = false;
        do {
            cityName = ToolsViewCls.getString(message);

            // Checks
            if (cityName.equals("0")) {
                exitDo = true;
                citiesNames.clear();
            } else if (cityName.equals("1")) {
                exitDo = true;
            } else if (citiesNames.contains(cityName)) {
                message = ToolsViewCls.getLangText(337) + ToolsViewCls.getLangText(336);
            } else if (configControlCls.checkCity(cityName) == 2) {
                message = ToolsViewCls.getLangText(335) + ToolsViewCls.getLangText(336);
            } else {
                citiesNames.add(cityName);
                message = ToolsViewCls.getLangText(336);
            }

        } while (!exitDo);

        //endregion ACTIONS


        // OUT
        return citiesNames;

    }

    /**
     * Method to add on ddbb the flights of trip.
     * @param newAirTripIn Class Air Trip, with list of flights.
     * @return false = doesn't add; true = added correctly.
     */
    public boolean addTrip_Flights(AirTrip newAirTripIn) {
        //region DEFINITION VARIABLES
        boolean exitDo = false, resul = false;
        List<Flight> flightsList = new ArrayList<>();

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        do {
            // Select departure city


            // Select departure time


        } while (!exitDo);

        //endregion ACTIONS


        // OUT
        return resul;

    }

    /**
     * Method to insert on the ddbb,
     *
     * @param newAirTripIn     AirTrip class.
     * @param citiesNameListIn List of cities names.
     * @return 0 = all good; 1 = some problem occurred.
     */
    public boolean addTrip_NewAir(AirTrip newAirTripIn, List<String> citiesNameListIn) {
        //region DEFINITION VARIABLES
        boolean resul = false;
        int addResult, messageCode = 0;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        addResult = configControlCls.addAirTrip(newAirTripIn, citiesNameListIn);
        switch (addResult) {
            case 0 -> {         // added correctly
                messageCode = 362;
                resul = true;
            }
            case 1 -> {         // some error with DDBB
                messageCode = 363;
                resul = true;
            }
            case 2, 3 -> {         // city class is null OR citisNameList empty
                messageCode = 321;
                resul = true;
            }
        }

        //endregion ACTIONS


        // OUT
        ToolsViewCls.pressAnyKey("\n" + ToolsViewCls.getLangText(messageCode));
        return resul;

    }

    //endregion METHOD: PRIVATES OF ADD AIR TRIPS


    //region METHOD: PRIVATES OF ADD LAND TRIPS

    private void addTrip_LandTrip() {
        //region DEFINITION VARIABLES
        boolean exitdo = false;
        int step = 0;
        List<String> citiesList = new ArrayList<>();
        List<Hotel> hotelsList = new ArrayList<>();
        LandTrip newLandTrip = new LandTrip();

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        do {
            switch (step) {
                case 0 -> {         // Insert Trip Name
                    newLandTrip.setName(addTrip_InsertName());
                    exitdo = newLandTrip.getName().length() <= 0;
                    step = 1;
                }
                case 1 -> {         // Insert trip duration
                    newLandTrip.setTripDays(addTrip_InsertDays());
                    exitdo = newLandTrip.getTripDays() == 0;
                    step = 2;
                }
                case 2 -> {         // Insert citys
                    citiesList.addAll(addTrip_Cities());
                    exitdo = citiesList.size() <= 0;
                    step = 3;
                }
                case 3 ->         // Insert hotels
                    ////*exitdo = addTrip_Hotels(newLandTrip);
                    ////*ToolsViewCls.pressAnyKey("\n" + ToolsViewCls.getLangText(2001));
                    ////*exitdo = true;
                        step = 4;
                case 4 -> {         // Add new Land Trip
                    exitdo = addTrip_NewLand(newLandTrip, citiesList);
                    step = 6;
                }
                default -> exitdo = true;
            }

        } while (!exitdo);

        //endregion ACTIONS


        // OUT

    }

    /**
     * Method to ask the user what insert the hotels what trips will visit.
     *
     * @param newLandTripIn Land Trip class where save the hotels.
     * @return false = all ok; true = some problem occurred.
     */
    private boolean addTrip_Hotels(LandTrip newLandTripIn) {
        //region DEFINITION VARIABLES
        boolean resul = false;

        //endregion DEFINITION VARIABLES


        //region ACTIONS

        //endregion ACTIONS


        // OUT
        return resul;

    }

    private boolean addTrip_NewLand(LandTrip newLandTripIn, List<String> citiesNameListIn) {
        //region DEFINITION VARIABLES
        boolean resul = false;
        int addResult, messageCode = 0;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        addResult = configControlCls.addLandTrip(newLandTripIn, citiesNameListIn);
        switch (addResult) {
            case 0 -> {             // added correctly
                messageCode = 364;
                resul = true;
            }
            case 1 ->               // some error with DDBB
                    messageCode = 365;
            case 2, 3 ->            // city class is null OR citisNameList empty
                    messageCode = 321;
        }

        //endregion ACTIONS


        // OUT
        ToolsViewCls.pressAnyKey("\n" + ToolsViewCls.getLangText(messageCode));
        return resul;

    }

    //endregion METHOD: PRIVATES OF ADD LAND TRIPS

}
