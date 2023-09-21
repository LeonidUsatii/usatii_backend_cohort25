package de.ait.task_04.servises;

import de.ait.task_04.dto.EventDto;
import de.ait.task_04.dto.NewEventDto;
import de.ait.task_04.dto.UpdateEventDto;

import java.util.List;

public interface EventsService {
    EventDto addEvent(NewEventDto newEvent);

    List<EventDto> getAllEvents();


    EventDto getEvent(Long id);

    EventDto updateEvent(Long id, UpdateEventDto updateEvent);

    EventDto deleteEvent(Long id);
}
