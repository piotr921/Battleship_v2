package com.spanishinquisition.battleship.common;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Test
public class HeaderTest {

    public void testParseResponseHeader_validHeader() {
        String givenString = "NAME";
        Header expectedHeader = Header.parseResponseHeader(givenString);
        assertEquals(expectedHeader, Header.NAME, "Wrong parsing of valid header");
    }

    public void testParseResponseHeader_invalidHeader() {
        String givenString = "";
        Header expectedEnum = Header.parseResponseHeader(givenString);
        assertEquals(expectedEnum, Header.UNKNOWN, "Wrong parsing of invalid header");
    }
}