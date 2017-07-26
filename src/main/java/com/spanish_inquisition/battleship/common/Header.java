package com.spanish_inquisition.battleship.common;

import java.util.logging.Level;

import static com.spanish_inquisition.battleship.common.AppLogger.logger;

/**
 * @author Michal_Partacz
 * A enum that will be sent with the requests and response to ther server, a server will identify the message by it's
 * header value
 */
public enum Header {
    NAME,
    UNKNOWN;

    /**
     * A method which will return a Header enum for an input string. In case there is no enum that can be inferred from
     * the string, it will return an UNKNOWN enum value.
     *
     * @param responseHeader a string which corresponds to the Headers name
     * @return a Header enum value parsed from the string
     */
    public static Header parseResponseHeader(String responseHeader) {
        Header header;
        try {
            header = valueOf(responseHeader);
        } catch (IllegalArgumentException e) {
            logger.log(Level.WARNING, e.getMessage(), e);
            header = UNKNOWN;
        }
        return header;
    }
}