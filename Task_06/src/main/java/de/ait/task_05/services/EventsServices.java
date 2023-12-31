package de.ait.task_05.services;

import de.ait.task_05.dto.*;

import java.util.List;

public interface EventsServices {
    EventDto addEvent(NewEventDto newEvent);

    List<EventDto> getEvents();

    EventDto getEvent(Long eventId);

    EventDto addPlaceToEvent(Long placeId, NewEventDto newEvent);

    List<EventDto> getEventsOfPlace(Long eventId);

    List<ParticipantDto> addParticipantToEvent(Long eventId, ParticipantToEventDto participantData);

    List<ParticipantDto> getParticipantOfEvent(Long eventId);
}
