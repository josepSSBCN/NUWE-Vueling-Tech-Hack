package org.hackVueling.view;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Classe with tools can be used multiple parts of program.
 */
public class ToolsViewCls {

    //region ATTRIBUTES STATICS
    private static int language = 0;

    // Screen names
    private static String addCity = "ADD CITY";
    private static String addFlight = "ADD FLIGHT";
    private static String addHotel = "ADD HOTEL";
    private static String addTrip = "ADD TRIP";
    private static String changeLanguage = "CHANGE LANGUAGE";
    private static String configMenu = "CONFIG MENU";
    private static String mainMenu = "MAIN MENU";
    private static String searchTrip = "SEARCH TRIP";
    private static String showResults = "SHOW TRIPS FOUND";

    //endregion ATTRIBUTES


    //region GETTERS & SETTERS
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

    public static String getChangeLanguage() {
        return changeLanguage;
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

    /**
     * Method to create screen's title with title received.
     * @param titleText Title text to show.
     * @return The title created.
     */
    public static String title(String titleText) {

        //region DEFINITION VARIABLES
        String text, text2;
        DateTimeFormatter daTeForm = DateTimeFormatter.ofPattern("HH:mm - dd/MM/yy");
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
        mainMessage = (mainMessage.length() == 0) ? ToolsViewCls.getLangText(1001) : mainMessage;
        errorMessage = (errorMessage.length() == 0) ? String.format(ToolsViewCls.getLangText(1002),
                minLimitIn, maxLimitIn) : errorMessage;

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
                System.out.println(ToolsViewCls.getLangText(1003));
            }
        } while (!exit);

        //endregion ACTIONS


        // OUT
        return result;

    }

    /**
     * Method to ask the user to insert a string.
     * @param mainMessage If is necessary show another message. If isn't necessary, send empty string.
     * @return The text
     */
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
                    System.out.println(ToolsViewCls.getLangText(1004));
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

    /**
     * Method to ask the user to insert a departure time.
     * @return String with departure time with format "HH:mm:ss"
     */
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
                System.out.print(ToolsViewCls.getLangText(1005));
                result = myScanner.nextLine();

                if (result.isEmpty() || result.isBlank()) {
                    System.out.println(ToolsViewCls.getLangText(1006));
                } else if (result.equals("0")) {
                    exit = true;
                } else if (result.length() > 5) {
                    System.out.println(ToolsViewCls.getLangText(1007));
                } else {
                    LocalTime time = LocalTime.parse(result + ":00");
                    exit = true;
                }

            } catch (Exception ex) {
                System.out.println(ToolsViewCls.getLangText(1007));
            }
        } while (!exit);

        //endregion ACTIONS


        // OUT
        return result;

    }

    /**
     * Method to wiat until user push some key.
     * @param message If is necessary show some message
     */
    public static void pressAnyKey(String message) {
        //region DEFINITION VARIABLES
        Scanner myScanner;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try {
            message = (message.length() > 0) ? message + ToolsViewCls.getLangText(1008) : ToolsViewCls.getLangText(1008);
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

    /**
     * Method to return a text of the code, depending on language selected.
     * @param codeText Code of text need.
     * @return The text.
     */
    public static String getLangText(int codeText) {
        String text = "";

        switch (language) {
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
            default:
                text = "Error TExt";
                break;
        }

        return text;
    }

    /**
     * Method to get English version of text code
     * @param codeText Code of text need
     * @return The text.
     */
    private static String inglishText(int codeText) {

        return switch (codeText) {
            case 100 -> "%s1.- SEARCH TRIP\n2.- CONFIGURATION\n3.- CHANGE LANGUAGE\n0.- EXIT\n";
            case 101 -> "\nThanks for your visit!\nSee you soon!";
            case 200 -> "\nEnter city's name (or part), to search (0 to back): ";
            case 201 -> "Sorry, we haven't trips that visit the city ";
            case 300 -> "%s\n\n" +
                    "1.- ADD CITY\n" +
                    "2.- ADD FLIGHT\n" +
                    "3.- ADD HOTEL\n" +
                    "4.- ADD TRIP\n" +
                    "0.- RETURN\n";
            case 301 -> "Please, enter the city's name (or 0 to exit): ";
            case 302 -> "\nCity '%s' added correctly!";
            case 303 -> "\nError during city saving, please try again later.";
            case 304 -> "This city already exists.\nPlease, enter another city's name (or 0 to exit): ";
            case 305 -> "The name is empty.\nPlease, enter correctly city's name (or 0 to exit): ";
            case 310 -> "Please, enter deparute city (0 to exit): ";
            case 311 -> "\nFlight added correctly!";
            case 312 -> "Error during save flight, please try again later.";
            case 313 -> "This city don't exists.";
            case 320 -> "Please, enter hotel's name (0 to exit): ";
            case 321 -> "Some error occurred, please try again later.";
            case 322 -> "Exit\nPlease, select hotel's category: ";
            case 323 -> "\nPlease, enter hotel's city name (0 to exit): ";
            case 324 -> "This city dosen't exist to travel.\nPlease, enter another name for hotel's city (0 to exit): ";
            case 325 -> "\nNew Hotel added correctly.";
            case 326 -> "This hotel's name already exist.\n";
            case 330 -> "\nPlease, insert trip name (0 to exit): ";
            case 331 -> "Trip already exist, please try again.\n";
            case 332 -> "1.- Air Trip\n2.- Land Trip\n0.- Return\n";
            case 333 -> "\nInsert trip's duration days (0 to exit): ";
            case 334 -> "\nPlease, enter city's name for visited (0 to exit - 1 to continuous): ";
            case 335 -> "This city dosen't exist to travel.";
            case 336 -> "\nPlease, enter another city's name to visited (0 to exit - 1 to continuous): ";
            case 337 -> "This city already exist.";
            case 360 -> "1.- Inglish\n2.- Español\n3.- 中国人\n4.- عرب\n5.- Français\n6.- Deutsch\n0.- Return";
            case 361 -> "Sorry, we are working to have this language, as soon as possible.";
            case 362 -> "Air Trip added correctly.";
            case 363 -> "Some error occurred during saving Air Trip, please try again later.";
            case 364 -> "Land Trip added correctly.";
            case 365 -> "Some error occurred during saving Land Trip, please try again later.";
            case 1001 -> "Select an option: ";
            case 1002 -> "Number out of limits, please try again (%s to %s).";
            case 1003 -> "That isn't a number, please try again.";
            case 1004 -> "It's necessary insert a city name, please try again.";
            case 1005 -> "Please, enter departure time (format HH:mm)(0 to exit): ";
            case 1006 -> "It's necessary insert departure time, please try again.";
            case 1007 -> "'Departure time' format is wrong.";
            case 1008 -> "\nPress any key and \"Enter\" to continue...";
            case 2000 -> "Error during process, please contact with technical service.";
            case 2001 -> "Sorry, some unexpected error occurred, please try again later.";
            default -> "Error Text";
        };
    }

    /**
     * Method to get Spanish version of text code.
     * @param codeText Code of text need
     * @return The text.
     */
    private static String spanishText(int codeText) {

        return switch (codeText) {
            case 100 -> "%s1.- BUSCAR VIAJE\n2.- CONFIGURACION\n3.- CAMBIO DE IDIOMA\n0.- SALIR\n";
            case 101 -> "\nGracias por tu visita!\n¡Hasta pronto!";
            case 200 -> "\nIntroduzca el nombre de la ciudad (o parte), para buscar (0 para volver): ";
            case 201 -> "Lo sentimos, pero no tenemos viajes que visiten la ciudad ";
            case 300 -> "%s\n\n" +
                    "1.- AÑADIR CIUDAD\n" +
                    "2.- AÑADIR VUELO\n" +
                    "3.- AÑADIR HOTEL\n" +
                    "4.- AÑADIR VIAJE\n" +
                    "0.- VOLVER\n";
            case 301 -> "Por favor, introduzca el nombre de la ciudad (o 0 para salir): ";
            case 302 -> "\n¡Ciudad'%s' añadida correctamente!";
            case 303 -> "Error al guardar la ciudad, inténtalo de nuevo más tarde.";
            case 304 -> "Esta ciudad ya existe.\nPor favor, introduzca el nombre de otra ciudad (o 0 para salir): ";
            case 305 -> "El nombre está vacío.\nPor favor, introduzca correctamente el nombre de la ciudad (o 0 para salir): ";
            case 310 -> "Por favor, introduzca la ciudad de salida (0 para salir): ";
            case 311 -> "\n¡Vuelo añadido correctamente!";
            case 312 -> "Error al guardar el vuelo, inténtalo de nuevo más tarde.";
            case 313 -> "Esta ciudad no existe.";
            case 320 -> "Por favor, introduzca el nombre del hotel (0 para salir): ";
            case 321 -> "Ocurrió algún error, inténtalo de nuevo más tarde.";
            case 322 -> "Volver\nPor favor, seleccione la categoría del hotel: ";
            case 323 -> "\nPor favor, introduzca el nombre de la ciudad del hotel (0 para salir): ";
            case 324 -> "Esta ciudad no existe para viajar.\nPor favor, introduzca otro nombre para la ciudad del hotel (0 para salir): ";
            case 325 -> "\nNuevo Hotel añadido correctamente.";
            case 326 -> "El nombre de este hotel ya existe.\n";
            case 330 -> "\nPor favor, introduzca el nombre del viaje (0 para salir): ";
            case 331 -> "El viaje ya existe, por favor, inténtalo de nuevo.\n";
            case 332 -> "1.- Viaje aereo\n2.- Viaje por tierra\n0.- Volver\n";
            case 333 -> "\nInserte los días de duración del viaje (0 para salir): ";
            case 334 -> "\nPor favor, introduzca el nombre de la ciudad para visitar (0 para salir - 1 para continuar): ";
            case 335 -> "Esta ciudad no existe para viajar.";
            case 336 -> "\nPor favor, introduzca el nombre de otra ciudad para visitar (0 para salir - 1 para continuar): ";
            case 337 -> "Esta ciudad ya existe.";
            case 360 -> "1.- Inglish\n2.- Español\n3.- 中国人\n4.- عرب\n5.- Français\n6.- Deutsch\n0.- Return";
            case 361 -> "Lo sentimos, estamos trabajando para tener este idioma en la máxima brevedad posible.";
            case 362 -> "Air Trip creado correctamente!";
            case 363 -> "Se produjo un error al guardar Air Trip, inténtelo de nuevo más tarde.";
            case 364 -> "Land Trip creado correctamente!";
            case 365 -> "Se produjo un error al guardar Land Trip, inténtelo de nuevo más tarde.";
            case 1001 -> "Seleccione una opción: ";
            case 1002 -> "Número fuera de los límites, por favor inténtelo de nuevo (%s a %s).";
            case 1003 -> "Esto no es un numero, por favor inténtelo de nuevo.";
            case 1004 -> "Es necesario introducir el nombre de una ciudad, por favor inténtelo de nuevo.";
            case 1005 -> "Por favor, introduzca la hora de salida (formato HH:mm)(0 para salir): ";
            case 1006 -> "Es necesario insertar hora de salida, por favor inténtelo de nuevo (0 para salir).";
            case 1007 -> "El formato de 'Hora de salida' es incorrecto.";
            case 1008 -> "\nPresione cualquier tecla y \"Entrar\" para continuar...";
            case 2000 -> "Error durante el proceso, por favor contacte con servicio técnico.";
            case 2001 -> "Lo sentimos, se produjo un error inesperado, inténtelo de nuevo más tarde.";
            default -> "Error Text";
        };
    }

    //endregion LANGUAGE MANAGE

}

