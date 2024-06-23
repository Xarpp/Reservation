package com.youplay.reservation.services;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.youplay.reservation.models.Host;
import com.youplay.reservation.models.Reservation;
import com.youplay.reservation.models.Status;
import com.youplay.reservation.models.modelsDTO.HostDTO;
import com.youplay.reservation.models.modelsDTO.ReservationDTO;
import com.youplay.reservation.repositories.ReservationRepository;
import com.youplay.reservation.utils.ReservationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final GizmoApiService gizmoApiService;
    @Autowired
    ReservationService(ReservationRepository reservationRepository, GizmoApiService gizmoApiService) {
        this.reservationRepository = reservationRepository;
        this.gizmoApiService = gizmoApiService;
    }

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

    public Reservation updateStatus(Long id, Status newStatus) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> new RuntimeException("Бронировние не найдено."));
        reservation.setStatus(newStatus);
        return reservationRepository.save(reservation);
    }

    public boolean setIsDeleted(Long id) {
        return reservationRepository.setIsDeletedStatusByID(id) >= 1;
    }

    public Long getReservationNumber(Long id) {
        return reservationRepository.findReservationNumberByReservationID(id);
    }


    public Reservation updateOrCreateReservationDB(Reservation reservation) throws JsonProcessingException {
        Reservation existingReservation = findReservationById(reservation.getReservationID());
        if (existingReservation != null && (existingReservation.getReservationNumber() > 0)) {
                reservation.setReservationNumber(existingReservation.getReservationNumber());
                updateReservationAPI(reservation);
        } else {
            reservation.setReservationNumber(createReservationAPI(reservation));
        }
        return reservationRepository.save(reservation);
    }

    public Reservation findReservationById(long reservationID) {
        return reservationRepository.findById(reservationID).orElse(null);
    }


    public Long createReservationAPI(Reservation reservation) throws JsonProcessingException {
        ReservationDTO reservationDTO = getReservationDTO(reservation);
        return gizmoApiService.createReservation(reservationDTO).getResult().getId();
    }

    public void updateReservationAPI (Reservation reservation) throws JsonProcessingException {
        ReservationDTO reservationDTO = getReservationDTO(reservation);

        gizmoApiService.updateReservation(reservationDTO);
    }

    public void deleteReservationNumber(Reservation reservation) {
        long reservationNumber = reservation.getReservationNumber();
        if (reservationNumber > 0)
            gizmoApiService.deleteReservation(reservationNumber);

        reservation.setReservationNumber(0);
        reservationRepository.save(reservation);



    }

    public ReservationDTO getReservationDTO(Reservation reservation) {
        List<HostDTO> hostDTOList = new ArrayList<>();

        Duration duration = Duration.between(reservation.getReservationDateStart(), reservation.getReservationDateEnd());
        int minutes = (int) duration.toMinutes();

        Set<Host> reservationHosts =  reservation.getHosts();

        for (Host reservationHost : reservationHosts) {
            hostDTOList.add(new HostDTO(reservationHost.getHostID()));
        }

        ReservationDTO reservationDTO = new ReservationDTO(
                reservation.getReservationDateStart(),
                minutes, reservation.getComment(),
                ReservationUtils.generatePin(),
                hostDTOList
        );

       if (reservation.getReservationNumber() > 0)
           reservationDTO.setId(reservation.getReservationNumber());

       return reservationDTO;
    }
}
