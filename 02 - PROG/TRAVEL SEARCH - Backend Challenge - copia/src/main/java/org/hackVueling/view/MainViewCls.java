package org.hackVueling.view;

import java.util.concurrent.TimeUnit;

public class MainViewCls {

    //region ATTRIBUTES


    //endregion ATTRIBUTES


    //region CONSTRUCTOR
    public MainViewCls() {

    }

    //endregion CONSTRUCTOR


    //region GETTERS & SETTERS

    //endregion GETTERS & SETTERS


    //region METHODS
    public void MainMenu() {
        //region DEFINITION VARIABLES
        boolean exit;
        int optionSel;
        String text;
        SearchViewCls searchViewCls;
        ConfigViewCls configViewCls;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try {
            // INIT
            exit = false;
            searchViewCls = SearchViewCls.getInstance();
            configViewCls = ConfigViewCls.getInstance();


            do {
                // TITLE
                text = ToolsViewCls.title(ToolsViewCls.getMainMenu());

                // BODY
                ////*text = text.concat(String.format("%s1.- SEARCH TRIP\n2.- CONFIGURATION\n0.- EXIT", "\n\n"));
                text = text.concat(String.format(ToolsViewCls.getText(100), "\n\n"));

                // DATA IN
                System.out.println(text);
                optionSel = ToolsViewCls.getInt(2, 0,"","");

                // SELECTION MANGE
                switch (optionSel) {
                    case 0:
                        exit = true;
                        break;
                    case 1:
                        searchViewCls.SerachTrip();
                        break;
                    case 2:
                        configViewCls.ConfigMenu();
                        break;
                    default:
                        // TODO thinking what todo
                        break;
                }

            } while (!exit);

        } catch (Exception ex) {
            // TODO execpcion control
        }

        //endregion ACTIONS


        // OUT


    }

    public void WelcomeScreen() throws Exception {
        //region DEFINITION VARIABLES
        String text;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        text = "\nWELCOME BIENVENIDO 欢迎 مرحباً BIENVENU WILLKOMMEN";
        text = String.format("%s\n%32s",text, "OPA TRAVEL AGENCY");

        //endregion ACTIONS


        // OUT
        System.out.println(text);
        TimeUnit.SECONDS.sleep(2);

    }

    public void ExitScreen() {
        //region DEFINITION VARIABLES
        String text;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        // INIT

        // TITLE
        text = ToolsViewCls.title("OPA Travel Agency");

        // BODY
        text = text + "\nThanks for your visit!\nSee you soon!";

        //endregion ACTIONS


        // OUT
        System.out.println(text);


    }


    //endregion METHODS


}
