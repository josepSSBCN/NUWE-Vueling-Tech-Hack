package org.hackVueling.model.classes;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SearchModelClsTest {

    @Test
    void checkCityExist() {
        SearchModelCls searchModelCls = SearchModelCls.getInstance();

        try {
            assertEquals(true, searchModelCls.checkCityExist("Two"));
            assertEquals(true, searchModelCls.checkCityExist("TWO"));
            assertEquals(true, searchModelCls.checkCityExist("two"));

        } catch (SQLException e) {
            ////*throw new RuntimeException(e);
            e.printStackTrace();
        }
    }

    @Test
    void getLandTripOfIdList() {
        SearchModelCls searchModelCls = SearchModelCls.getInstance();

        try {
            searchModelCls.getLandTripOfIdList(Arrays.asList((short)1, (short)2, (short)3));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Test
    void getAirTripOfIdList() {
        SearchModelCls searchModelCls = SearchModelCls.getInstance();

        try {
            searchModelCls.getAirTripOfIdList(Arrays.asList((short)1, (short)2, (short)3));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getTripsWitCity() {
        SearchModelCls searchModelCls = SearchModelCls.getInstance();

        try {
            assertEquals(1, searchModelCls.getAirTripsWitCity("One").size());
            assertEquals(4, searchModelCls.getAirTripsWitCity("TWO").size());
            assertEquals(4, searchModelCls.getLandTripsWitCity("NINE").size());
            assertEquals(3, searchModelCls.getLandTripsWitCity("FIVE").size());


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}