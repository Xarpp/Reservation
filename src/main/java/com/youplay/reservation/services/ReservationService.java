package com.youplay.reservation.services;


import com.youplay.reservation.models.Reservation;
import com.youplay.reservation.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation> getDataTableByStatus(String status) {
        return switch (status) {
            case "canceled" -> reservationRepository.findByStatusIn(Arrays.asList(3L, 4L));
            case "accepted" -> reservationRepository.findByStatusIn(List.of(1L));
            default -> reservationRepository.findByStatusIn(Arrays.asList(2L, 5L));
        };
    }
}
