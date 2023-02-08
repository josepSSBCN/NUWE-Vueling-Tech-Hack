package org.hackVueling.model.dataStructrure;

import java.util.HashMap;
import java.util.Map;

/**
 * Contains the basics data structures, that isn't need saved at DDBB
 * Is static because all access to same data
 */
public class BasicStructs {
    //region ATTRIBUTES PUBLICS
    public static enum enumCategorys{
        One(1, "1*"),
        Two(2, "2*"),
        Three(3, "3*"),
        Fort(4, "4*"),
        Five(5, "5*"),
        Six(6, "5*S");

        private static final Map<Integer, enumCategorys> BY_ID = new HashMap<>();
        private static final Map<String, enumCategorys> BY_NAME = new HashMap<>();

        static{
            for (enumCategorys e: values()) {
                BY_ID.put(e.id, e);
                BY_NAME.put(e.name, e);
            }
        }

        public final int id;
        public final String name;


        enumCategorys(int id, String name) {
            this.name = name;
            this.id = id;
        }

        public static enumCategorys valueOfId(int id) {
            return BY_ID.get(id);
        }

        public static String nameOfId(int id) {
            return BY_ID.get(id).name;
        }

        public static enumCategorys valueOfName(String name) {
            return BY_NAME.get(name);
        }
    }

    public static enum enumTrips{
        AirTrip(0, false, "Air Trip"),
        LandTrip(1, true,  "Land Trip");

        private static final Map<Integer, enumTrips>BY_ID = new HashMap<>();
        private static final Map<String , enumTrips>BY_NAME = new HashMap<>();
        private static final Map<Boolean, enumTrips>BY_BOOLEAN = new HashMap<>();

        static {
            for (enumTrips e:values()) {
                BY_ID.put(e.id, e);
                BY_BOOLEAN.put(e.blnn, e);
                BY_NAME.put(e.name, e);
            }
        }

        public final int id;
        public final boolean blnn;
        public final String name;

        enumTrips(int id, boolean blnn, String name) {
            this.id = id;
            this.blnn = blnn;
            this.name = name;
        }

        public static enumTrips valueOfId(int id){
            return BY_ID.get(id);
        }
        public static enumTrips valueOfId(boolean blnn){
            return BY_BOOLEAN.get(blnn);
        }
        public static enumTrips valueOfName(String name){
            return BY_NAME.get(name);
        }
    }


    //endregion ATTRIBUTES PUBLICS


    //region CONSTRUCTOR

    //endregion CONSTRUCTOR


    //region GETTERS & SETTERS

    //endregion GETTERS & SETTERS


    //region METHODS

    //endregion METHODS


}
