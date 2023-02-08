package org.hackVueling.view;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ToolsViewClsTest {

    @Test
    void getDepartureTime() {

        assertEquals("12:00",ToolsViewCls.getDepartureTime());
    }
}