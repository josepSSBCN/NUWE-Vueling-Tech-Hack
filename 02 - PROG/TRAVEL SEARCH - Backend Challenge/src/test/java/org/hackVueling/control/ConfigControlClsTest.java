package org.hackVueling.control;

import org.hackVueling.model.classes.ConfigModelCls;
import org.hackVueling.model.dataStructrure.AirTrip;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ConfigControlClsTest {

    @Test
    void addAirTrip() {
        AirTrip airTrip = new AirTrip((short) 0, "Travel 234", (short) 4, null, null);
        List<String> cicitesNames = new ArrayList<>(Arrays.asList("Barcelona", "Tarragona"));

        assertEquals(0, ConfigControlCls.getInstance().addAirTrip(airTrip, cicitesNames));
        assertEquals(3, ConfigControlCls.getInstance().addAirTrip(airTrip, new ArrayList<>()));
        assertEquals(3, ConfigControlCls.getInstance().addAirTrip(null, new ArrayList<>()));

    }
}