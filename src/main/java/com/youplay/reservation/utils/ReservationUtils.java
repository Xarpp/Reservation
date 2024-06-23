package com.youplay.reservation.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class ReservationUtils {

    public static String dateFormatterAsString(LocalDateTime localDateTime, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return localDateTime.format(formatter);
    }

    public static LocalDateTime dateFormatterAsLocalDate(String formattedDateTime, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.parse(formattedDateTime, formatter);
    }

    public static String generatePin() {
        Random random = new Random();
        StringBuilder pinBuilder = new StringBuilder();

        for (int i = 0; i < 6; i++) {
            int index = random.nextInt(52);
            char symbol = (index < 26)? (char) ('A' + index) : (char) ('0' + index - 26);
            pinBuilder.append(symbol);
        }
        return pinBuilder.toString();
    }
}
