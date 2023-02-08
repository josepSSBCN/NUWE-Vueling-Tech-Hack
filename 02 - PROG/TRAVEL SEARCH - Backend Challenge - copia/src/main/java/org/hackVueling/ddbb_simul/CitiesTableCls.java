package org.hackVueling.ddbb_simul;

public class CitiesTableCls {
    //region ATTRIBUTES
    private Short id;
    private String name;

    //endregion ATTRIBUTES


    //region CONSTRUCTOR

    public CitiesTableCls() {

    }

    public CitiesTableCls(Short id, String name) {
        this.id = id;
        this.name = name;
    }

    public CitiesTableCls(CitiesTableCls citiesTableCls) {
        this.id = citiesTableCls.id;
        this.name = citiesTableCls.name;
    }

    //endregion CONSTRUCTOR


    //region GETTERS & SETTERS

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //endregion GETTERS & SETTERS


    //region METHODS

    //endregion METHODS


}
