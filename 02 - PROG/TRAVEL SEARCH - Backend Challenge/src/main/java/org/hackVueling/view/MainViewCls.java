package org.hackVueling.view;

import java.util.concurrent.TimeUnit;

/**
 * Classe to control main screens, welcome, main menu, exit.
 */
public class MainViewCls {
    //region CONSTRUCTOR
    public MainViewCls() {

    }

    //endregion CONSTRUCTOR


    //region METHODS

    /**
     * Method to show and manage screen 'Main Menu'.
     */
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
                text = text.concat(String.format(ToolsViewCls.getLangText(100), "\n\n"));

                // DATA IN
                System.out.println(text);
                optionSel = ToolsViewCls.getInt(3, 0,"","");

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
                    case 3:
                        ChangeLanguage();
                    default:
                        ToolsViewCls.getLangText(2000);
                        break;
                }

            } while (!exit);

        } catch (Exception ex) {
            ToolsViewCls.pressAnyKey(ToolsViewCls.getLangText(2000));
        }

        //endregion ACTIONS

    }

    /**
     * Method to show and manage screen 'Change Language'.
     */
    public void ChangeLanguage() {
        //region DEFINITION VARIABLES
        int option;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try {
            // TITLE
            System.out.println(ToolsViewCls.title(ToolsViewCls.getChangeLanguage()) + "\n");

            // BODY
            System.out.println(ToolsViewCls.getLangText(360));

            // USER INSERT DATA
            option = ToolsViewCls.getInt(6, 0, "", "");
            switch (option) {
                case 0:
                    break;
                case 1:         // English
                    ToolsViewCls.setLanguage(0);
                    break;
                case 2:         // Spanish
                    ToolsViewCls.setLanguage(1);
                    break;
                default:        // Others
                    ToolsViewCls.pressAnyKey(ToolsViewCls.getLangText(361));
                    break;
            }

        } catch (Exception ex) {
            ToolsViewCls.pressAnyKey(ToolsViewCls.getLangText(2000));
        }

        //endregion ACTIONS

    }

    /**
     * Method to show screen 'Welcome':
     * @throws Exception
     */
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
        TimeUnit.SECONDS.sleep(1);

    }

    /**
     * Method to show screen 'Exit'.
     */
    public void ExitScreen() {
        //region DEFINITION VARIABLES
        String text;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        // TITLE
        text = ToolsViewCls.title("OPA Travel Agency");

        // BODY
        text = text + "\n" + ToolsViewCls.getLangText(101);

        //endregion ACTIONS


        // OUT
        System.out.println(text);

    }

    //endregion METHODS

}
