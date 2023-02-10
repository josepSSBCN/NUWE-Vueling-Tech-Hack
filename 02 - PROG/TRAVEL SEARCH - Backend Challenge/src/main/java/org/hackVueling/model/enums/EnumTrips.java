package org.hackVueling.model.enums;

import java.util.HashMap;
import java.util.Map;

public enum EnumTrips {
    AirTrip(0, false, "Air Trip"),
    LandTrip(1, true,  "Land Trip");

    private static final Map<Integer, EnumTrips> BY_ID = new HashMap<>();
    private static final Map<String , EnumTrips>BY_NAME = new HashMap<>();
    private static final Map<Boolean, EnumTrips>BY_BOOLEAN = new HashMap<>();

    static {
        for (EnumTrips e:values()) {
            BY_ID.put(e.id, e);
            BY_BOOLEAN.put(e.blnn, e);
            BY_NAME.put(e.name, e);
        }
    }

    public final int id;
    public final boolean blnn;
    public final String name;

    EnumTrips(int id, boolean blnn, String name) {
        this.id = id;
        this.blnn = blnn;
        this.name = name;
    }

    public static EnumTrips valueOfId(int id){
        return BY_ID.get(id);
    }
    public static EnumTrips valueOfId(boolean blnn){
        return BY_BOOLEAN.get(blnn);
    }
    public static EnumTrips valueOfName(String name){
        return BY_NAME.get(name);
    }
}
