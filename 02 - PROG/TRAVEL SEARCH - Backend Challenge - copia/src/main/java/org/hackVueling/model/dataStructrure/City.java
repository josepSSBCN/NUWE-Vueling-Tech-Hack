package org.hackVueling.model.dataStructrure;

public class City {
    //region ATTRIBUTES
    private Short id;
    private String name;

    //endregion ATTRIBUTES


    //region CONSTRUCTOR
    public City(){

    }
    public City(short idIn, String nameIn){
        this.id = idIn;
        this.name = nameIn;
    }
    public City (City cityIn){
        this.id = cityIn.id;
        this.name = cityIn.name;
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
