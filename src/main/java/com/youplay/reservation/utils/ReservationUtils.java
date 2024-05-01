package com.youplay.reservation.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReservationUtils {

    public static String dateFormatterAsString(LocalDateTime localDateTime, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return localDateTime.format(formatter);
    }

    public static LocalDateTime dateFormatterAsLocalDate(String formattedDateTime, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.parse(formattedDateTime, formatter);
    }
}
