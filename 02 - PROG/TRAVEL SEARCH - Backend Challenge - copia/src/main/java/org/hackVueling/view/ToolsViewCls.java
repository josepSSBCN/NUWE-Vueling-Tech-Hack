package org.hackVueling.view;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ToolsViewCls {

    //region ATTRIBUTES STATICS
    private static int language = 0;

    // Screen names
    private static String addCity = "ADD CITY";
    private static String addFlight = "ADD FLIGHT";
    private static String addHotel = "ADD HOTEL";
    private static String addTrip = "ADD TRIP";
    private static String configMenu = "CONFIG MENU";
    private static String mainMenu = "MAIN MENU";
    private static String searchTrip = "SEARCH TRIP";
    private static String showResults = "SHOW TRIP FOUND";

    //endregion ATTRIBUTES


    //region GETTERS & SETTERS

    public static int getLanguage() {
        return language;
    }

    public static void setLanguage(int language) {
        ToolsViewCls.language = language;
    }

    public static String getAddCity() {
        return addCity;
    }

    public static String getAddFlight() {
        return addFlight;
    }

    public static String getAddHotel() {
        return addHotel;
    }

    public static String getAddTrip() {
        return addTrip;
    }

    public static String getConfigMenu() {
        return configMenu;
    }

    public static String getMainMenu() {
        return mainMenu;
    }

    public static String getSearchTrip() {
        return searchTrip;
    }

    public static String getShowResults() {
        return showResults;
    }

    //endregion GETTERS & SETTERS


    //region METHODS
    public static String title(String titleText) {

        //region DEFINITION VARIABLES
        String text, text2;
        DateTimeFormatter daTeForm = DateTimeFormatter.ofPattern("HH:mm - dd/MM/YY");
        LocalDateTime now = LocalDateTime.now();

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        text2 = String.format("%" + ((29 - titleText.length()) / 2) + "s", " ").replace(' ', '*');
        text = "\n\n************************************************************\n";
        text = text.concat(text2);
        text = text.concat(" " + titleText + " ");
        text = text.concat(text2);
        text = text.concat(String.format("%" + (28 - daTeForm.format(now).length()) + "s", " ").replace(' ', '*'));
        text = text.concat(" " + daTeForm.format(now));

        //endregion ACTIONS


        // OUT
        return text;

    }

    /**
     * Method to user inset a integer value, controlled a minimum value and maximum value
     *
     * @param maxLimitIn Max value to control
     * @param minLimitIn Min value to control
     * @return put value. Error values: minLimitIn-1 => out of limimts; minLimitIn-2 =>not a number
     */
    public static int getInt(int maxLimitIn, int minLimitIn, String mainMessage, String errorMessage) {
        //region DEFINITION VARIABLES
        boolean exit = false;
        int result = 0;
        Scanner myScanner;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        mainMessage = (mainMessage.length()==0)? "Select an option: ": mainMessage;
        errorMessage = (errorMessage.length() == 0)? String.format("Number out of limits, please try again, %s to %s .",
                minLimitIn, maxLimitIn): errorMessage;

        do {
            try {
                myScanner = new Scanner(System.in);
                System.out.print(mainMessage);
                result = myScanner.nextInt();

                if (result > maxLimitIn || minLimitIn > result) {
                    System.out.println(errorMessage);
                } else {
                    exit = true;
                }
            } catch (Exception ex) {
                System.out.println("That isn't a number, please try again.");
            }
        } while (!exit);

        //endregion ACTIONS


        // OUT
        return result;

    }

    public static String getString(String mainMessage) {
        //region DEFINITION VARIABLES
        boolean exit = false;
        String result = "";
        Scanner myScanner;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        do {
            try {
                myScanner = new Scanner(System.in);
                System.out.print(mainMessage);
                result = myScanner.nextLine();

                if (result.isBlank() || result.isEmpty()) {
                    System.out.println("It's necessary insert a city name, please try again.");
                } else {
                    exit = true;
                }

            } catch (Exception ex) {
                System.out.println("Good joke! Try again.");
            }
        } while (!exit);

        //endregion ACTIONS


        // OUT
        return result;

    }

    public static String getDepartureTime() {
        //region DEFINITION VARIABLES
        boolean exit = false;
        String result = "";
        Scanner myScanner;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        do {
            try {
                myScanner = new Scanner(System.in);
                System.out.print("Please, enter departure time (format HH:mm)(0 to exit): ");
                result = myScanner.nextLine();

                if (result.isEmpty() || result.isBlank()) {
                    System.out.println("It's necessary insert a name.");
                } else if (result.equals("0")) {
                    exit = true;
                } else if (result.length() > 5) {
                    System.out.println("'Departure time' format is wrong.");
                } else {
                    LocalTime time = LocalTime.parse(result + ":00");
                    exit = true;
                }

            } catch (Exception ex) {
                System.out.println("'Departure time' format is wrong.");
            }
        } while (!exit);

        //endregion ACTIONS


        // OUT
        return result;

    }

    public static void getAnyKeyPressed(String message) {
        //region DEFINITION VARIABLES
        Scanner myScanner;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try {
            message = (message.length()>0)? message + "\nPress any key and \"Enter\" to continue...":"Press any key and \"Enter\" to continue...\"";
            System.out.print(message);
            myScanner = new Scanner(System.in);
            myScanner.nextLine();

        } catch (Exception ex) {
            System.out.println("Good joke! Try again.");
        }

        //endregion ACTIONS

    }

    //endregion METHODS

    //region LANGUAGE MANAGE

    public static String getText(int codeText){
        String text = "";

        switch (language){
            case 0:         // Inglish
                text = inglishText(codeText);
                break;
            case 1:         // Spanish
                text = spanishText(codeText);
                break;
            case 2:         // Chinese
                break;
            case 3:         // Arab
                break;
            case 4:         //
                break;
        }

        return text;
    }

    private static String inglishText(int codeText){
        String text = "";

        switch (codeText){
            case 100:
                text = "%s1.- SEARCH TRIP\n2.- CONFIGURATION\n0.- EXIT";
                break;
            case 101:
                break;
        }

        return text;
    }

    private static String spanishText(int codeText){
        String text = "";

        switch (codeText){
            case 100:
                text ="%s1.- BUSCAR VIAJE\n2.- CONFIGURACION\n0.- SALIR";
                break;
            case 101:
                break;
        }

        return text;
    }

    //endregion LANGUAGE MANAGE

}

