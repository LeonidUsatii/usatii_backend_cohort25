package de.ait.task_05.services.impl;

import de.ait.task_05.dto.EventDto;
import de.ait.task_05.dto.NewEventDto;
import de.ait.task_05.dto.NewPlaceDto;
import de.ait.task_05.dto.PlaceDto;
import de.ait.task_05.services.EventsServices;

import java.util.List;

public class EventsServicesImpl implements EventsServices {
    @Override
    public EventDto addEvent(NewEventDto newEvent) {
        return null;
    }

    @Override
    public List<EventDto> getEvents() {
        return null;
    }

    @Override
    public EventDto getEvent(Long eventId) {
        return null;
    }

    @Override
    public EventDto addPlaceToEvent(Long placeId, NewEventDto newEvent) {
        return null;
    }

    @Override
    public List<EventDto> getEventsOfPlace(Long eventId) {
        return null;
    }
}
