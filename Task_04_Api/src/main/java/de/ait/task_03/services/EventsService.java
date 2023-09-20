package de.ait.task_03.services;



import de.ait.task_03.dto.EventDto;
import de.ait.task_03.dto.NewEventDto;
import de.ait.task_03.dto.UpdateEventDto;
import de.ait.task_03.models.Event;

import java.util.List;

public interface EventsService {
    EventDto addEvent(NewEventDto newEvent);

    List<EventDto> getAllEvents();


    EventDto getEvent(Long id);

    EventDto updateEvent(Long id, UpdateEventDto updateEvent);

    EventDto deleteEvent(Long id);
}
