package org.hackVueling.view;

import org.hackVueling.control.SearchControlCls;

public class SearchViewCls {
    //region ATTRIBUTES
    private static SearchViewCls instance;
    private static SearchControlCls searchControlCls;


    //endregion ATTRIBUTES


    //region CONSTRUCTOR
    private SearchViewCls() {
        searchControlCls = SearchControlCls.getInstance();
    }

    public static SearchViewCls getInstance() {
        if (instance == null) {
            instance = new SearchViewCls();
        }
        return instance;
    }

    //endregion CONSTRUCTOR


    //region GETTERS & SETTERS

    //endregion GETTERS & SETTERS


    //region METHODS
    public void SerachTrip() {
        //region DEFINITION VARIABLES
        String text, cityUserIn;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try {
            // INIT

            // TITLE
            System.out.println(ToolsViewCls.title(ToolsViewCls.getSearchTrip()));

            // USER DATA IN
            cityUserIn = ToolsViewCls.getString("\nEnter city's name (or part), to search (0 to back): ");

            // CONTROL EXIT
            if (!cityUserIn.equals("0")) {
                // Check trips
                text = searchControlCls.findTripsWithCityName(cityUserIn);
                ShowResults(text);
            }

        } catch (Exception ex) {
            // TODO thinking what put here
        }

        //endregion ACTIONS

    }

    public void ShowResults(String tripsFound) {
        //region DEFINITION VARIABLES

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try {
            // SCREEN TITLE
            System.out.println(ToolsViewCls.title(ToolsViewCls.getShowResults()));

            // SCREEN BODY
            System.out.println("\n" + tripsFound);


            // USER DATA IN
            ToolsViewCls.getAnyKeyPressed("");


        } catch (Exception ex) {
            // TODO thinking what to do
        }
        //endregion ACTIONS


        // OUT


    }

    //endregion METHODS


}
