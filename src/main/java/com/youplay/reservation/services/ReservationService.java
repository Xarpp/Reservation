package com.youplay.reservation.services;


import com.youplay.reservation.models.Reservation;
import com.youplay.reservation.repositories.ReservationRepository;
import jakarta.transaction.Transactional;
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
            case "canceled" -> reservationRepository.getByStatus_statusIDIn(Arrays.asList(3L, 4L));
            case "accepted" -> reservationRepository.getByStatus_statusIDIn(List.of(1L));
            default -> reservationRepository.getByStatus_statusIDIn(Arrays.asList(2L, 5L));
        };
    }

    public boolean deleteRowFromDatabase(String cellValue) {
        try {
            if(reservationRepository.findById(Long.valueOf(cellValue)).isPresent()) {
                reservationRepository.deleteById(Long.valueOf(cellValue));
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
    public boolean setIsDeleted(Long id) {
        return reservationRepository.setIsDeletedStatusByID(id) >= 1;
    }

    @Transactional
    public Reservation updateOrCreateReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }
}
