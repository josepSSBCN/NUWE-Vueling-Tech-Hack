package org.hackVueling.view;

import org.hackVueling.control.ConfigControlCls;
import org.hackVueling.control.ToolsControlCls;
import org.hackVueling.model.dataStructrure.BasicStructs;

import javax.imageio.plugins.tiff.ExifTIFFTagSet;
import javax.swing.*;
import javax.swing.plaf.synth.SynthTextPaneUI;
import javax.swing.table.TableRowSorter;
import java.util.ArrayList;
import java.util.List;

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
    public boolean ConfigMenu() {
        //region DEFINITION VARIABLES
        boolean exit = false, resul = false;
        int optionSelected;
        String text;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try {
            // INIT


            do {
                // TITLE
                text = ToolsViewCls.title(ToolsViewCls.getConfigMenu());

                // BODY
                text = String.format("%s\n" +
                        "1.- ADD CITY\n" +
                        "2.- ADD FLIGHT\n" +
                        "3.- ADD HOTEL\n" +
                        "4.- ADD TRIP\n" +
                        "0.- RETURN\n", text);

                // USER DATA IN
                System.out.println(text);
                optionSelected = ToolsViewCls.getInt(4, 0, "", "");

                // SELECTION MANAGE
                switch (optionSelected) {
                    case 0:
                        exit = true;
                        break;
                    case 1:
                        AddCity();
                        break;
                    case 2:
                        AddFlight();
                        break;
                    case 3:
                        AddHotel();
                        break;
                    case 4:
                        AddTrip();
                        break;
                    default:
                        // TODO what put here?
                        break;
                }

            } while (!exit);
        } catch (Exception ex) {
            // TODO thinking what todo
        }
        //endregion ACTIONS


        // OUT
        return resul;

    }

    private boolean AddCity() {
        //region DEFINITION VARIABLES
        boolean resul = false, exitDo = false;
        int resulAddMet;
        String text = "", cityName, messageCity;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try {
            // INIT
            messageCity = "Please, enter the city's name (or enter 0 to exit): ";

            // TITLE
            System.out.println(ToolsViewCls.title(ToolsViewCls.getAddCity()) + "\n");

            // USER INSERT DATA
            // CITY NAME
            do {
                cityName = ToolsViewCls.getString(messageCity);

                if (!cityName.equals("0")) {
                    // Add city
                    resulAddMet = configControlCls.addCity(cityName);
                    switch (resulAddMet) {
                        case 0:
                            text = String.format("City '%s' added correctly!", cityName);
                            exitDo = true;
                            resul = true;
                            break;
                        case 1, 3:
                            text = "Error during city save, please try again later.";
                            exitDo = true;
                            resul = false;
                            break;
                        case 2:
                            messageCity = "This city already exists.\nPlease, enter another city's name (or enter 0 to exit): ";
                            break;
                        case 4:
                            messageCity = "The name is empty.\nPlease, enter correctly city's name (or enter 0 to exit): ";
                            break;
                    }
                } else {
                    exitDo = true;
                }

            } while (!exitDo);

        } catch (Exception ex) {
            text = "Error during process, please contact with technical service.";
        }

        //endregion ACTIONS


        // OUT
        System.out.println(text);
        return resul;

    }

    private boolean AddFlight() {
        //region DEFINITION VARIABLES
        boolean resul = false, exitDo = false;
        int resulAddMet;
        String message = "", text = "", cityName, departureTime;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try {
            // INIT
            message = "Please, enter deparute city (0 to exit): ";

            // TITLE
            System.out.println(ToolsViewCls.title(ToolsViewCls.getAddFlight()) + "\n");

            // INSERT THE CITY NAME
            do {
                cityName = ToolsViewCls.getString(message);
                if (!cityName.equals("0")) {
                    // INSERT THE DEPARTURE TIME
                    departureTime = ToolsViewCls.getDepartureTime();
                    if (!departureTime.equals("0")) {
                        // ADD NEW FLIGHT
                        resulAddMet = configControlCls.addFlight(cityName, departureTime);
                        switch (resulAddMet) {
                            case 0:
                                text = "Flight added correctly!";
                                exitDo = true;
                                resul = true;
                                break;
                            case 1, 3:
                                text = "Error during save flight, please try again later.";
                                exitDo = true;
                                resul = false;
                                break;
                            case 2:
                                message = "This city don't exists.\nPlease, enter another city's name (or enter 0 to exit): ";
                                break;
                        }
                    }
                }
            } while (!exitDo);

        } catch (Exception ex) {
            text = "Error during process, please contact with technical service.";
        }

        //endregion ACTIONS


        // OUT
        System.out.println(text);
        return resul;

    }

    private boolean AddHotel() {
        //region DEFINITION VARIABLES
        boolean exitMethod = false, exitDo = false;
        int step = 0, resulCheckHotel, idCategory = 0, resulAdded = 0;
        String message = "", hotelName = "", cityName = "";

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try {
            // TITLE
            System.out.println(ToolsViewCls.title(ToolsViewCls.getAddHotel()) + "\n");

            do {
                switch (step) {
                    case 0:     // Insert hotel's name
                        do {
                            message = message + "Please, enter hotel's name (0 to exit): ";
                            hotelName = ToolsViewCls.getString(message);

                            // Check if exist
                            resulCheckHotel = (hotelName.equals("0")) ? 1000 : configControlCls.checkHotel(hotelName);

                            switch (resulCheckHotel) {
                                case 0:
                                    exitDo = true;
                                    message="";
                                    break;
                                case 1:
                                    message = "Some error occurred, please try again later.";
                                    exitMethod = true;
                                    exitDo = true;
                                    break;
                                case 2:
                                    break;
                                case 1000:
                                    exitMethod = true;
                                    exitDo = true;
                                    message = "";
                                    break;
                                default:
                                    break;
                            }
                        } while (!exitDo);

                        if (message.length() > 0) System.out.println(message);

                        step++;
                        break;
                    case 1:     // Select hotel's category
                        message = String.format("\n1.- %s\n2.-%s\n3.-%s\n4.-%s\n5.-%s\n6.-%s\n0.- Exit\nPlease, select hotel's category: ",
                                BasicStructs.enumCategorys.One, BasicStructs.enumCategorys.Two,
                                BasicStructs.enumCategorys.Three, BasicStructs.enumCategorys.Fort,
                                BasicStructs.enumCategorys.Five, BasicStructs.enumCategorys.Six);

                        idCategory = ToolsViewCls.getInt(6, 0, message, "");
                        exitMethod = (idCategory == 0) ? true : false;

                        step++;
                        break;
                    case 2:     // Insert hotel's city.
                        message = "\nPlease, enter hotel's city name (0 to exit): ";
                        exitDo = false;
                        do {
                            cityName = ToolsViewCls.getString(message);
                            // Checks
                            if (cityName.equals("0")) {
                                exitDo = true;
                                exitMethod = true;
                            } else if (configControlCls.checkCity(cityName) == 2) {
                                message = "This city dosen't exist to travel.\nPlease, enter another name for hotel's city (0 to exit): ";
                            } else {
                                exitDo = true;
                            }
                        } while (!exitDo);

                        step++;
                        break;
                    case 3:     // Add new hotel
                        resulAdded = configControlCls.addHotel(hotelName, cityName, idCategory);

                        switch (resulAdded) {
                            case 0:
                                message = "New Hotel added correctly.";
                                exitMethod = true;
                                break;
                            default:
                                message = "Sorry, some unexpected error occurred, please try again later.";
                                exitMethod = true;
                                break;
                        }

                        step++;
                        break;
                    default:
                        message = "Sorry, some unexpected error occurred, please try again later.";
                        exitMethod = true;
                        break;
                }

            } while (!exitMethod);

            ToolsViewCls.getAnyKeyPressed(message);

        } catch (Exception ex) {

        }
        //endregion ACTIONS


        // OUT
        return false;

    }

    private boolean AddTrip() {
        //region DEFINITION VARIABLES
        /**
         * true = Air Trip; false = Land Trip
         */
        boolean trpType, exitDo = false, exitMethod = false;
        int tripDays, resulCheckTrip, step = 0, tempInt = 0;
        String tripName = "", message = "", cityName = "";
        List<String> citiesNames = new ArrayList<>();

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        // INIT

        // TITLE
        System.out.println(ToolsViewCls.title(ToolsViewCls.getAddTrip()) + "\n");

        // GET TRIP NAME
        do {
            switch (step) {
                case 0:     // Insert Trip Name
                    do {
                        message = message + "Please, insert trip name (0 to exit): ";
                        tripName = ToolsViewCls.getString(message);

                        // Check if exist
                        resulCheckTrip = (tripName.equals("0")) ? 1000 : configControlCls.checkTrip(tripName);

                        switch (resulCheckTrip) {
                            case 0:
                                exitDo = true;
                                message = "";
                                break;
                            case 1:
                                message = "Some error occurred, please try again later.";
                                exitMethod = true;
                                exitDo = true;
                                break;
                            case 2:
                                message = "Trip exist, please try again.\n";
                                break;
                            case 1000:
                                exitMethod = true;
                                exitDo = true;
                                message = "";
                                break;
                            default:
                                break;
                        }
                    } while (!exitDo);

                    if (message.length() > 0) System.out.println(message);
                    step++;
                    break;
                case 1:     // Select trip type
                    System.out.println("\n1.- Air Trip\n2.- Land Trip\n0.- Exit");

                    do {
                        tempInt = ToolsViewCls.getInt(3, 0, "", "");
                        switch (tempInt) {
                            case 0:
                                exitDo = true;
                                exitMethod = true;
                                break;
                            case 1:
                                trpType = false;
                                exitDo = true;
                                break;
                            case 2:
                                trpType = true;
                                exitDo = true;
                                break;
                            default:
                                break;
                        }
                    } while (!exitDo);

                    step++;
                    break;
                case 2:     // Insert trip duration
                    tempInt = ToolsViewCls.getInt(365, 0, "\nInsert trip duration days (0 to exit): ",
                            "");
                    if (tempInt == 0) {
                        exitMethod = true;
                    } else {
                        tripDays = tempInt;
                    }

                    step++;
                    break;
                case 3:     // Insert citys visited
                    message = "\nPlease, enter a city name visited (0 to exit): ";
                    exitDo = false;
                    do {
                        cityName = ToolsViewCls.getString(message);

                        // Checks
                        if (cityName.equals("0")) {
                            exitDo = true;
                            exitMethod = true;
                        } else if (citiesNames.contains(cityName)) {
                            message = "This city already exist.\nPlease, enter another city name to visited (0 to exit): ";
                        } else if (configControlCls.checkCity(cityName) == 2) {
                            message = "This city dosen't exist to travel.\nPlease, enter another city name to visited (0 to exit): ";
                        } else {
                            citiesNames.add(cityName);
                            message = "Please, enter another city name visited (0 to exit): ";
                        }

                    } while (!exitDo);

                    step++;
                    break;
                case 4:     // Is air trip, add flights

                    break;
                case 5:     // Is land trip, add hotels

                    break;
                default:
                    break;
            }

        } while (!exitMethod);


        //endregion ACTIONS


        // OUT
        return false;

    }

    //endregion METHODS


    //region METHODS: PRIVATES


    //region METHODS: PRIVATES

}
