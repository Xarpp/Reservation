package com.youplay.reservation.validation;

import com.youplay.reservation.models.Reservation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class DateRangeValidator implements ConstraintValidator<ValidReservationDateRange, Reservation> {
    @Override
    public boolean isValid(Reservation reservation, ConstraintValidatorContext context) {
        if (reservation == null) {
            return true;
        }
        return!reservation.getReservationDateStart().isAfter(reservation.getReservationDateEnd());
    }
}
