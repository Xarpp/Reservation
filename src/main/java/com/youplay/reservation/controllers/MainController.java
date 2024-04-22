package com.youplay.reservation.controllers;

import com.youplay.reservation.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    private ReservationRepository reservationRepository;

    @GetMapping("/")
    public String actual(Model model) {
        model.addAttribute("title", "Система бронирования игровых мест YouPlay");
        return "home";
    }

}