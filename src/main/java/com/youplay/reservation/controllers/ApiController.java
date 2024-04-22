package com.youplay.reservation.controllers;

import com.youplay.reservation.models.Reservation;
import com.youplay.reservation.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping("/getTableData")
    public ResponseEntity<List<Reservation>> getTableData(@RequestParam String status) {
        List<Reservation> data = reservationService.getDataTableByStatus(status);

        return ResponseEntity.ok(data);
    }
}
