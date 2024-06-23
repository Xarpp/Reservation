package com.youplay.reservation.validation;

import java.lang.annotation.*;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = DateRangeValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ValidReservationDateRange {
    String message() default "Начальная дата должна быть раньше конечной";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
