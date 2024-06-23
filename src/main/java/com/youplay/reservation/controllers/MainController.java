package com.youplay.reservation.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String actual(Model model) {
        model.addAttribute("title", "Система бронирования игровых мест YouPlay");
        return "home";
    }

}