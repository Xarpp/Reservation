package com.youplay.reservation.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.youplay.reservation.models.*;
import com.youplay.reservation.services.HostService;
import com.youplay.reservation.services.PlatformService;
import com.youplay.reservation.services.ReservationService;
import com.youplay.reservation.services.StatusService;
import com.youplay.reservation.services.GizmoApiService;
import com.youplay.reservation.validation.ValidReservationDateRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Value("${reservation.gizmo_url}")
    private String GIZMO_URL;
    private final ReservationService reservationService;
    private final PlatformService platformService;
    private final StatusService statusService;
    private final HostService hostService;

    private final GizmoApiService gizmoApiService;
    @Autowired
    public ApiController(ReservationService reservationService, PlatformService platformService, StatusService statusService, HostService hostService, GizmoApiService gizmoApiService) {
        this.reservationService = reservationService;
        this.platformService = platformService;
        this.statusService = statusService;
        this.hostService = hostService;
        this.gizmoApiService = gizmoApiService;
    }

    @GetMapping("/getTableData")
    public ResponseEntity<List<Reservation>> getTableData(@RequestParam String status) {
        try {
            List<Reservation> data = reservationService.getDataTableByStatus(status);
            return ResponseEntity.status(201).body(data);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/getPlatformList")
    public List<Platform> getPlatformList() {
        return platformService.getAllPlatforms();
    }

    @GetMapping("/getPcList")
    public ResponseEntity<List<Host>> getPcList() {
        try {
            List<Host> hosts = gizmoApiService.getPcList();
            if (hosts != null) {
                List<Host> addHosts = hostService.saveAll(hosts);
                addHosts.sort(Comparator.comparing(Host::getNumber));
                return ResponseEntity.status(201).body(addHosts);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }


    @PostMapping("/deleteRow")
    public ResponseEntity<String> deleteRow(@RequestParam("id") String id) {
        long reservationId = Long.parseLong(id);
        boolean isDeleted = reservationService.setIsDeleted(reservationId);
        if (isDeleted) {
            reservationService.deleteReservationNumber(reservationService.findReservationById(reservationId));
            return ResponseEntity.ok("Строка успешно удалена");
        } else {
            return ResponseEntity.badRequest().body("Ошибка при удалении строки");
        }
    }

    @PostMapping("/reservation")
    public ResponseEntity<String> createReservation(@RequestBody Reservation newReservation) throws JsonProcessingException {
        String successMessage = "Бронирование успешно создано / изменено";
        String errorMessage = "Ошибка при созданиии/изменении бронирования";
        try {
            Reservation savedReservation = reservationService.updateOrCreateReservationDB(newReservation);
            if (savedReservation != null) {
                return ResponseEntity.ok(successMessage);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return ResponseEntity.badRequest().body(errorMessage);
    }
    @PostMapping("/platform")
    public ResponseEntity<Platform> createPlatform(@RequestBody Platform newPlatform) {
        Platform savedPlatform = platformService.addNewPlatform(newPlatform);
        if (savedPlatform != null) {
            return ResponseEntity.status(201).body(savedPlatform);
        } else {
            return ResponseEntity.status(500).body(null);
        }
    }

    @PostMapping("/reservationNumber")
    public ResponseEntity<Long> getReservationNumber(@RequestParam("id") String id) {
        Long reservationNumber = reservationService.getReservationNumber(Long.valueOf(id));
        return ResponseEntity.ok(reservationNumber);
    }

    @PutMapping("/changeStatus")
    public ResponseEntity<String> changeStatusById(@RequestParam("id") String id, @RequestParam("status") String status) {
        try {
            long reservationId = Long.parseLong(id);
            long statusId = Long.parseLong(status);

            Reservation reservation = reservationService.findReservationById(reservationId);

            String successMessage = "Статус успешно изменен.";
            String errorMessage = "Не удалось обновить статус бронирования.";

            Status newStatus = statusService.getStatusById(statusId);

            if (reservationService.updateStatus(reservationId, newStatus) == null) {
                return ResponseEntity.badRequest().body(errorMessage);
            }

            if (reservation.getReservationNumber() > 0)
                reservationService.deleteReservationNumber(reservation);
            else
                reservationService.updateOrCreateReservationDB(reservation);

            return ResponseEntity.ok(successMessage);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Внутренняя ошибка сервера");
        }
    }
}
