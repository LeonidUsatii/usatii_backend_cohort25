package de.ait.task_05.services;

import de.ait.task_05.dto.EventDto;
import de.ait.task_05.dto.NewEventDto;
import de.ait.task_05.dto.NewPlaceDto;
import de.ait.task_05.dto.PlaceDto;

import java.util.List;

public interface EventsServices {
    EventDto addEvent(NewEventDto newEvent);

    List<EventDto> getEvents();

    EventDto getEvent(Long eventId);

    EventDto addPlaceToEvent(Long placeId, NewEventDto newEvent);

    List<EventDto> getEventsOfPlace(Long eventId);
}
