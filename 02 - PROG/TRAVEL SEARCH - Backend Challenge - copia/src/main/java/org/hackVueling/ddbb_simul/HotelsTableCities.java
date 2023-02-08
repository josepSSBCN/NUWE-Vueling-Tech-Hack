package org.hackVueling.ddbb_simul;

public class HotelsTableCities {
    //region ATTRIBUTES
    private Short id;
    private Short idCity;
    private String name;
    private Short getIdCity;

    //endregion ATTRIBUTES


    //region CONSTRUCTOR

    public HotelsTableCities() {
    }

    public HotelsTableCities(Short id, Short idCity, String name, Short getIdCity) {
        this.id = id;
        this.idCity = idCity;
        this.name = name;
        this.getIdCity = getIdCity;
    }

    public HotelsTableCities(HotelsTableCities hotelsTableCities){
        this.id = hotelsTableCities.id;
        this.idCity = hotelsTableCities.idCity;
        this.name = hotelsTableCities.name;
        this.getIdCity = hotelsTableCities.getIdCity;

    }

    //endregion CONSTRUCTOR


    //region GETTERS & SETTERS

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public Short getIdCity() {
        return idCity;
    }

    public void setIdCity(Short idCity) {
        this.idCity = idCity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Short getGetIdCity() {
        return getIdCity;
    }

    public void setGetIdCity(Short getIdCity) {
        this.getIdCity = getIdCity;
    }

    //endregion GETTERS & SETTERS


    //region METHODS

    //endregion METHODS


}
