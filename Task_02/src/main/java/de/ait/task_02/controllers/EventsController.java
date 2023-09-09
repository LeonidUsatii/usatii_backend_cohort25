package de.ait.task_02.controllers;


import de.ait.task_02.models.Event;
import de.ait.task_02.services.EventsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

import static java.lang.Integer.parseInt;

/**
 * 9/6/2023
 * mvc-demo
 *
 * @author Marsel Sidikov (AIT TR)
 */
@Controller
public class EventsController {

    private final EventsService eventsService;

    public EventsController(EventsService eventsService) {
        this.eventsService = eventsService;
    }

    @PostMapping("/signUp")
    public String addEvent(@RequestParam("titleOfEvent") String title,
                          @RequestParam("startDate") String startDateForm,
                          @RequestParam("expirationDate") String expirationDateForm
                         ) {
        String[] parsed = startDateForm.split("-");
        int year = Integer.parseInt(parsed[0]);
        int month = Integer.parseInt(parsed[1]);
        int day = Integer.parseInt(parsed[2]);
        LocalDate startDate = LocalDate.of(year, month, day);

        parsed = expirationDateForm.split("-");
        year = Integer.parseInt(parsed[0]);
        month = Integer.parseInt(parsed[1]);
        day = Integer.parseInt(parsed[2]);
        LocalDate expirationDate = LocalDate.of(year, month, day);

        eventsService.addEvent(title, startDate, expirationDate);
        return "redirect:/success_signUp.html";
    }

    @GetMapping("/events")
    public String getEventsPage(Model model) {
        List<Event> events = eventsService.getAllEvents();
        model.addAttribute("eventsList", events);
        return "events_page";
    }



}
