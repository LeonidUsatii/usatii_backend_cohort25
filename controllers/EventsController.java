package de.ait.task_02.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
public class EventsController {
    @PostMapping("/signUp")
    public String addEvent(@RequestParam("title") String title,
                           @RequestParam("startDate") LocalDate startDate,
                           @RequestParam("expirationDate") LocalDate expirationDate) {
        System.out.println(title + " " + startDate + " " + expirationDate);
        return null;
    }
}
