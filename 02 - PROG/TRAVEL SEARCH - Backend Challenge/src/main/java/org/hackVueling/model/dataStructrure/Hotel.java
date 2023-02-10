package org.hackVueling.model.dataStructrure;

/**
 * Structure classe to hotels.
 */
public class Hotel {
    //region ATTRIBUTES
    /**
     * Hotel's id.
     */
    private short id;
    /**
     * Hotel's name.
     */
    private String name;
    /**
     * City's id of where the hotel is located.
     */
    private short idCity;
    /**
     * City's name of where the hotel is located.
     */
    private String cityName;
    /**
     * Category's id from EnumCategory.
     */
    private byte idCategory;

    //endregion ATTRIBUTES


    //region CONSTRUCTOR

    public Hotel() {
    }

    public Hotel(short id, String name, short idCity, String cityName, byte category) {
        this.id = id;
        this.name = name;
        this.idCity = idCity;
        this.cityName = cityName;
        this.idCategory = category;
    }

    public Hotel (Hotel hotelIn){
        this.id = hotelIn.id;
        this.name = hotelIn.name;
        this.idCity = hotelIn.idCity;
        this.cityName = hotelIn.cityName;
        this.idCategory = hotelIn.idCategory;
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

    public byte getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(byte idCategory) {
        this.idCategory = idCategory;
    }

    //endregion GETTERS & SETTERS

}
