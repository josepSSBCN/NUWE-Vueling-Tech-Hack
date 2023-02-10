package org.hackVueling.view;

import org.hackVueling.control.SearchControlCls;

/**
 * Classe to control all actions of search on View layer.
 */
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


    //region METHODS

    /**
     * Method to control search a
     */
    public void SerachTrip() {
        //region DEFINITION VARIABLES
        String text, cityUserIn;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try {
            // TITLE
            System.out.println(ToolsViewCls.title(ToolsViewCls.getSearchTrip()));

            // USER DATA IN
            cityUserIn = ToolsViewCls.getString(ToolsViewCls.getLangText(200));

            // CONTROL EXIT
            if (!cityUserIn.equals("0")) {
                // Check trips
                text = searchControlCls.findTripsWithCityName(cityUserIn);
                ShowResults(text, cityUserIn);
            }

        } catch (Exception ex) {
            ToolsViewCls.pressAnyKey(ToolsViewCls.getLangText(2000));
        }

        //endregion ACTIONS

    }

    /**
     * Method to show screen 'results found'.
     * @param tripsFound Text with results.
     */
    public void ShowResults(String tripsFound, String cityNameIn) {
        //region ACTIONS
        try {
            // SCREEN TITLE
            System.out.println(ToolsViewCls.title(ToolsViewCls.getShowResults()));

            // SCREEN BODY
            if (tripsFound.length()==0) {
                tripsFound = ToolsViewCls.getLangText(201) + "'" + cityNameIn + "'.\n";
            }

            // USER DATA IN
            ToolsViewCls.pressAnyKey("\n" + tripsFound);

        } catch (Exception ex) {
            ToolsViewCls.pressAnyKey(ToolsViewCls.getLangText(2000));
        }

        //endregion ACTIONS

    }

    //endregion METHODS

}
