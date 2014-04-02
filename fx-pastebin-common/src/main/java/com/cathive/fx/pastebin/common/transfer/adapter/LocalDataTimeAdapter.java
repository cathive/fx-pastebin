package com.cathive.fx.pastebin.common.transfer.adapter;

import java.time.LocalDateTime;

import static java.time.format.DateTimeFormatter.ISO_DATE_TIME;

/**
 * Static adapter method that can handle the new java.time classes
 * correctly.
 * @author Benjamin P. Jung
 */
public class LocalDataTimeAdapter {

    public static String printLocalDateTime(final LocalDateTime localDateTime) {
        return localDateTime.format(ISO_DATE_TIME);
    }


    public static LocalDateTime parseLocalDateTime(final String string) {
        return LocalDateTime.parse(string, ISO_DATE_TIME);
    }

}
