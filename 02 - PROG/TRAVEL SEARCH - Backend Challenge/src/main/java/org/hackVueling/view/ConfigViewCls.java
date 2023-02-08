package org.hackVueling.view;

import org.hackVueling.control.ConfigControlCls;
import org.hackVueling.model.dataStructrure.BasicStructs;

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


    //region GETTERS & SETTERS

    //endregion GETTERS & SETTERS


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
            System.out.println(ToolsViewCls.getLangText(2000));
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
                        case 0 -> {
                            text = String.format(ToolsViewCls.getLangText(302), cityName);
                            exitDo = true;
                        }
                        case 1, 3 -> {
                            text = ToolsViewCls.getLangText(303);
                            exitDo = true;
                        }
                        case 2 -> messageCity = ToolsViewCls.getLangText(304);
                        case 4 -> messageCity = ToolsViewCls.getLangText(305);
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
        ToolsViewCls.pressAnyKey(text);

    }

    /**
     * Method to add a new flight on ddbb. This method ask step by step, all info it needed.
     */
    private void AddFlight() {
        //region DEFINITION VARIABLES
        boolean exitMethod = false;
        int resulAddMet, step = 0;
        String text = "", cityName = "", departureTime = "";

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try {
            // TITLE
            System.out.println(ToolsViewCls.title(ToolsViewCls.getAddFlight()) + "\n");

            // INSERT THE CITY NAME
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
                        resulAddMet = configControlCls.addFlight(cityName, departureTime);
                        switch (resulAddMet) {
                            case 0 -> {
                                text = ToolsViewCls.getLangText(311);
                                exitMethod = true;
                            }
                            case 1, 3 -> {
                                text = ToolsViewCls.getLangText(312);
                                exitMethod = true;
                            }
                            case 2 -> {
                                System.out.println(ToolsViewCls.getLangText(313));
                                step = 0;
                            }
                        }
                    }
                    default -> text = ToolsViewCls.getLangText(2001);
                }
            } while (!exitMethod);

        } catch (Exception ex) {
            text = ToolsViewCls.getLangText(2000);
        }

        //endregion ACTIONS


        // OUT
        ToolsViewCls.pressAnyKey(text);

    }

    /**
     * Method to add a new hotel on ddbb. This method ask step by step, all info it needed.
     */
    private void AddHotel() {
        //region DEFINITION VARIABLES
        boolean exitMethod = false, exitDo = false;
        int step = 0, resulCheckHotel, idCategory = 0, resulAdded;
        String message = "", hotelName = "", cityName = "";

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try {
            // TITLE
            System.out.println(ToolsViewCls.title(ToolsViewCls.getAddHotel()) + "\n");

            do {
                switch (step) {
                    case 0 -> {     // Insert hotel's name
                        do {
                            message = message + ToolsViewCls.getLangText(320);
                            hotelName = ToolsViewCls.getString(message);

                            // Check if exist
                            resulCheckHotel = (hotelName.equals("0")) ? 1000 : configControlCls.checkHotel(hotelName);

                            switch (resulCheckHotel) {
                                case 0 -> {
                                    exitDo = true;
                                    message = "";
                                }
                                case 1 -> {
                                    message = ToolsViewCls.getLangText(321);
                                    exitMethod = true;
                                    exitDo = true;
                                }
                                case 2 -> message = ToolsViewCls.getLangText(326);
                                case 1000 -> {
                                    exitMethod = true;
                                    exitDo = true;
                                    message = "";
                                }
                                default -> {
                                }
                            }
                        } while (!exitDo);
                        if (message.length() > 0) System.out.println(message);
                        step = 1;
                    }
                    case 1 -> {     // Select hotel's category
                        message = String.format("\n1.- %s\n2.- %s\n3.- %s\n4.- %s\n5.- %s\n6.- %s\n0.- %s", BasicStructs.enumCategorys.One, BasicStructs.enumCategorys.Two, BasicStructs.enumCategorys.Three, BasicStructs.enumCategorys.Fort, BasicStructs.enumCategorys.Five, BasicStructs.enumCategorys.Six, ToolsViewCls.getLangText(322));
                        idCategory = ToolsViewCls.getInt(6, 0, message, "");
                        exitMethod = idCategory == 0;
                        step = 2;
                    }
                    case 2 -> {     // Insert hotel's city.
                        message = ToolsViewCls.getLangText(323);
                        exitDo = false;
                        do {
                            cityName = ToolsViewCls.getString(message);
                            // Checks
                            if (cityName.equals("0")) {
                                exitDo = true;
                                exitMethod = true;
                                message = "";
                            } else if (configControlCls.checkCity(cityName) == 2) {
                                message = ToolsViewCls.getLangText(324);
                            } else {
                                exitDo = true;
                            }
                        } while (!exitDo);
                        step = 3;
                    }
                    case 3 -> {     // Add new hotel
                        resulAdded = configControlCls.addHotel(hotelName, cityName, idCategory);
                        if (resulAdded == 0) {
                            message = ToolsViewCls.getLangText(325);
                            exitMethod = true;
                        } else {
                            message = ToolsViewCls.getLangText(2002);
                            exitMethod = true;
                        }
                        step++;
                    }
                    default -> {
                        message = ToolsViewCls.getLangText(2002);
                        exitMethod = true;
                    }
                }

            } while (!exitMethod);

            ToolsViewCls.pressAnyKey(message);

        } catch (Exception ex) {
            System.out.println(ToolsViewCls.getLangText(2001));
        }
        //endregion ACTIONS

    }

    /**
     * Method to add a new trip on ddbb. This method ask step by step, all info it needed.
     */
    private void AddTrip() {
        //region DEFINITION VARIABLES
        boolean trpType, exitDo = false, exitMethod = false;
        int tripDays, resulCheckTrip, step = 0, tempInt;
        String tripName, message = "", cityName;
        List<String> citiesNames = new ArrayList<>();

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try {
            // TITLE
            System.out.println(ToolsViewCls.title(ToolsViewCls.getAddTrip()) + "\n");

            do {
                switch (step) {
                    case 0:     // Insert Trip Name
                        do {
                            message = message + ToolsViewCls.getLangText(330);
                            tripName = ToolsViewCls.getString(message);

                            // Check if exist
                            resulCheckTrip = (tripName.equals("0")) ? 1000 : configControlCls.checkTrip(tripName);

                            switch (resulCheckTrip) {
                                case 0 -> {
                                    exitDo = true;
                                    message = "";
                                }
                                case 1 -> {
                                    message = ToolsViewCls.getLangText(321);
                                    exitMethod = true;
                                    exitDo = true;
                                }
                                case 2 -> message = ToolsViewCls.getLangText(331);
                                case 1000 -> {
                                    exitMethod = true;
                                    exitDo = true;
                                    message = "";
                                }
                                default -> ToolsViewCls.getLangText(2002);
                            }
                        } while (!exitDo);

                        if (message.length() > 0) System.out.println(message);
                        step = 1;
                        break;
                    case 1:     // Select trip type
                        exitDo = false;
                        System.out.println(ToolsViewCls.getLangText(332));

                        do {
                            tempInt = ToolsViewCls.getInt(3, 0, "", "");
                            switch (tempInt) {
                                case 0 -> {
                                    exitDo = true;
                                    exitMethod = true;
                                }
                                case 1 -> {
                                    trpType = false;
                                    exitDo = true;
                                }
                                case 2 -> {
                                    trpType = true;
                                    exitDo = true;
                                }
                                default -> {
                                }
                            }
                        } while (!exitDo);

                        step = 2;
                        break;
                    case 2:     // Insert trip duration
                        tempInt = ToolsViewCls.getInt(365, 0, ToolsViewCls.getLangText(333), "");
                        if (tempInt == 0) {
                            exitMethod = true;
                        } else {
                            tripDays = tempInt;
                        }

                        step = 3;
                        break;
                    case 3:     // Insert citys visited
                        message = ToolsViewCls.getLangText(334);
                        exitDo = false;
                        do {
                            cityName = ToolsViewCls.getString(message);

                            // Checks
                            if (cityName.equals("0")) {
                                exitDo = true;
                                exitMethod = true;
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

                        step = 4;
                        break;
                    case 4:     // Is air trip, add flights
                        ToolsViewCls.pressAnyKey("\n" + ToolsViewCls.getLangText(2001));
                        message = "";
                        exitMethod = true;
                        break;
                    case 5:     // Is land trip, add hotels
                        break;
                    case 6:     // Add new air trip
                        break;
                    case 7:     // Add new land trip
                        break;
                    default:
                        System.out.println(ToolsViewCls.getLangText(2000));
                        break;
                }

            } while (!exitMethod);
        } catch (Exception ex) {
            System.out.println(ToolsViewCls.getLangText(2000));
        }

        //endregion ACTIONS

    }

    //endregion METHODS: ADD

}
