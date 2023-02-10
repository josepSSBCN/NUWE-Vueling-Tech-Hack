package org.hackVueling.model.enums;


import java.util.HashMap;
import java.util.Map;

public enum EnumCategorys {
    One(1, "*"),
    Two(2, "**"),
    Three(3, "***"),
    Fort(4, "****"),
    Five(5, "*****"),
    Six(6, "*****S");

    private static final Map<Integer, EnumCategorys> BY_ID = new HashMap<>();
    private static final Map<String, EnumCategorys> BY_NAME = new HashMap<>();

    static{
        for (EnumCategorys e: values()) {
            BY_ID.put(e.id, e);
            BY_NAME.put(e.name, e);
        }
    }

    public final int id;
    public final String name;


    EnumCategorys(int id, String name) {
        this.name = name;
        this.id = id;
    }

    public static EnumCategorys valueOfId(int id) {
        return BY_ID.get(id);
    }

    public static String nameOfId(int id) {
        return BY_ID.get(id).name;
    }

    public static EnumCategorys valueOfName(String name) {
        return BY_NAME.get(name);
    }
}
