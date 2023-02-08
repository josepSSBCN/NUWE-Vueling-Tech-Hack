package org.hackVueling.model.dataStructrure;

/**
 * Structure classe to hotels.
 */
public class Hotel {
    //region ATTRIBUTES
    private short id;
    private String name;
    private short idCity;
    private String cityName;
    private byte category;

    //endregion ATTRIBUTES


    //region CONSTRUCTOR

    public Hotel() {
    }

    public Hotel(short id, String name, short idCity, String cityName, byte category) {
        this.id = id;
        this.name = name;
        this.idCity = idCity;
        this.cityName = cityName;
        this.category = category;
    }

    public Hotel (Hotel hotelIn){
        this.id = hotelIn.id;
        this.name = hotelIn.name;
        this.idCity = hotelIn.idCity;
        this.cityName = hotelIn.cityName;
        this.category = hotelIn.category;
    }

    //endregion CONSTRUCTOR


    //region GETTERS & SETTERS

    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    public short getIdCity() {
        return idCity;
    }

    public void setIdCity(short idCity) {
        this.idCity = idCity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public byte getCategory() {
        return category;
    }

    public void setCategory(byte category) {
        this.category = category;
    }

    //endregion GETTERS & SETTERS


}
