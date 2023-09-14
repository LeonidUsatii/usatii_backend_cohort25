package de.ait.task_03.controllers;


import de.ait.task_03.dto.EventDto;
import de.ait.task_03.models.Event;
import de.ait.task_03.services.EventsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.util.List;
@Tags(value = @Tag(name = "Events"))
@Controller
public class EventsController {

    private final EventsService eventsService;

    public EventsController(EventsService eventsService) {
        this.eventsService = eventsService;
    }

    @Operation(summary = "Получение всех событий", description = "Доступно администратору системы")
    @PostMapping("/events")
    @ResponseBody
    public String addEvent(@RequestBody EventDto eventDto
                           ) {
        System.out.println(eventDto);
        String[] parsed = (eventDto.getStartDate() + "") .split("-");
        int year = Integer.parseInt(parsed[0]);
        int month = Integer.parseInt(parsed[1]);
        int day = Integer.parseInt(parsed[2]);
        LocalDate startDate = LocalDate.of(year, month, day);

        parsed = (eventDto.getExpirationDate() + "").split("-");
        year = Integer.parseInt(parsed[0]);
        month = Integer.parseInt(parsed[1]);
        day = Integer.parseInt(parsed[2]);
        LocalDate expirationDate = LocalDate.of(year, month, day);

        EventDto event = new EventDto(eventDto.getTitle(), startDate, expirationDate);
        eventsService.addEvent(event);
        return "redirect:/success_signUp.html";
    }

    @GetMapping("/events")
    @ResponseBody
    public List<EventDto> getEvents() {
        List<EventDto> events = eventsService.getAllEvents();

        return eventsService.getAllEvents();
    }



}
