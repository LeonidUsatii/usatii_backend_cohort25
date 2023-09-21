package de.ait.task_04.controllers;

import de.ait.task_04.dto.EventDto;
import de.ait.task_04.dto.NewEventDto;
import de.ait.task_04.dto.UpdateEventDto;
import de.ait.task_04.servises.EventsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tags(value = @Tag(name = "Events"))
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/events")
public class EventsController {

    private final EventsService eventService;

    @Operation(summary = "Добавление нового события", description = "Доступно администратору системы")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EventDto addEvent(@RequestBody NewEventDto newEvent) {
        return eventService. addEvent(newEvent);
    }

    @Operation(summary = "Получение всех событий", description = "Доступно всем пользователям")
        @GetMapping
        public List<EventDto> getAllEvents() {
            System.out.println(Thread.currentThread().getName());
            return eventService.getAllEvents();
        }

    @Operation(summary = "Получение одного события", description = "Доступно всем пользователям")
    @GetMapping("/{event-id}")
    public EventDto getEvent(@Parameter(description = "идентификатор события", example = "4")
                             @PathVariable("event-id") Long id) {
        return eventService.getEvent(id);
    }

    @Operation(summary = "Обновление события", description = "Доступно только администратору")
    @PutMapping("/{event-id}")
    public EventDto updateEvent(@Parameter(description = "идентификатор события", example = "1")
                                @PathVariable("event-id") Long id,
                                @RequestBody UpdateEventDto updateEvent) {
        return eventService.updateEvent(id, updateEvent);
    }

    @Operation(summary = "Удаление события", description = "Доступно только администратору")
    @DeleteMapping("/{event-id}")
    public EventDto deleteEvent(@Parameter(description = "идентификатор события", example = "1")
                                @PathVariable("event-id") Long id) {
        return eventService.deleteEvent(id);
    }

}

