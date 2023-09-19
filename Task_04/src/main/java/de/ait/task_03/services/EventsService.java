package de.ait.task_03.services;



import de.ait.task_03.dto.EventDto;
import de.ait.task_03.dto.NewEventDto;
import de.ait.task_03.models.Event;

import java.util.List;

public interface EventsService {
    EventDto addEvent(NewEventDto newEvent);

    List<EventDto> getAllEvents();


}
