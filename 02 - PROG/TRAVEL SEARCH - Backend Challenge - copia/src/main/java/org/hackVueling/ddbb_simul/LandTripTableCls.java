package org.hackVueling.ddbb_simul;

import java.util.ArrayList;
import java.util.List;

public class LandTripTableCls {
    //region ATTRIBUTES
    private List<Short> id;
    private List<String> name;
    private List<Short> duration;
    private LandTripTableCls instance;

    //endregion ATTRIBUTES


    //region CONSTRUCTOR
    private LandTripTableCls() {
        id = new ArrayList<>();
        name = new ArrayList<>();
        duration = new ArrayList<>();

    }

    public LandTripTableCls getInstance() {
        if (instance == null) {
            instance = new LandTripTableCls();
        }
        return instance;
    }

    //endregion CONSTRUCTOR


    //region GETTERS & SETTERS

    //endregion GETTERS & SETTERS


    //region METHODS
    public boolean insert() {
        //region DEFINITION VARIABLES


        //endregion DEFINITION VARIABLES


        //region ACTIONS

        //endregion ACTIONS


        // OUT
        return true;

    }

    public boolean select(){
        //region DEFINITION VARIABLES


        //endregion DEFINITION VARIABLES


        //region ACTIONS

        //endregion ACTIONS


        // OUT
        return false;

    }

    public boolean Update(){
        //region DEFINITION VARIABLES


        //endregion DEFINITION VARIABLES


        //region ACTIONS

        //endregion ACTIONS


        // OUT
        return false;

    }

    public boolean delete(){
        //region DEFINITION VARIABLES


        //endregion DEFINITION VARIABLES


        //region ACTIONS

        //endregion ACTIONS


        // OUT
        return false;

    }

    //endregion METHODS


}
