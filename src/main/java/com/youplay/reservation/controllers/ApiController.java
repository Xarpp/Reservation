package com.youplay.reservation.controllers;

import com.youplay.reservation.models.Platform;
import com.youplay.reservation.models.Reservation;
import com.youplay.reservation.repositories.ReservationRepository;
import com.youplay.reservation.services.PlatformService;
import com.youplay.reservation.services.ReservationService;
import com.youplay.reservation.side_api.GizmoApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {


    private final ReservationService reservationService;
    private final PlatformService platformService;

    @Autowired
    public ApiController(ReservationService reservationService, ReservationRepository reservationRepository, PlatformService platformService) {
        this.reservationService = reservationService;
        this.platformService = platformService;
    }

    @GetMapping("/getTableData")
    public ResponseEntity<List<Reservation>> getTableData(@RequestParam String status) {
        List<Reservation> data = reservationService.getDataTableByStatus(status);

        return ResponseEntity.status(201).body(data);
    }

    @GetMapping("/getPlatformList")
    public List<Platform> getPlatformList() {
        return platformService.getAllPlatforms();
    }

    @GetMapping("/getPcList")
    public List<GizmoApi.PC> getPcList() {
        return GizmoApi.getPcList();
    }

    @PostMapping("/deleteRow")
    public ResponseEntity<String> deleteRow(@RequestParam("id") String id) {
        boolean isDeleted = reservationService.setIsDeleted(Long.valueOf(id));
        if (isDeleted) {
            return ResponseEntity.ok("Строка успешно удалена");
        } else {
            return ResponseEntity.badRequest().body("Ошибка при удалении строки");
        }
    }
    @PostMapping("/reservation")
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation newReservation) {
        Reservation savedReservation = reservationService.updateOrCreateReservation(newReservation);
        if (savedReservation!= null) {
            return ResponseEntity.status(201).body(savedReservation);
        } else {
            return ResponseEntity.status(500).body(null);
        }

    }

    @PostMapping("/platform")
    public ResponseEntity<Platform> createPlatform(@RequestBody Platform newPlatform) {
        Platform savedPlatform = platformService.addNewPlatform(newPlatform);
        if (savedPlatform!= null) {
            return ResponseEntity.status(201).body(savedPlatform);
        } else {
            return ResponseEntity.status(500).body(null);
        }
    }

}
