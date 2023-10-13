package de.ait.task_05.services.impl;

import de.ait.task_05.dto.*;
import de.ait.task_05.exceptions.RestException;
import de.ait.task_05.models.Event;
import de.ait.task_05.models.Participant;
import de.ait.task_05.models.Place;
import de.ait.task_05.repositories.EventsRepository;
import de.ait.task_05.repositories.ParticipantsRepository;
import de.ait.task_05.services.EventsServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

import static de.ait.task_05.dto.ParticipantDto.from;


@RequiredArgsConstructor
@Service
public class EventsServicesImpl implements EventsServices {
    private final EventsRepository eventsRepository;
    private final ParticipantsRepository participantsRepository;
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

    @Override
    public List<ParticipantDto> addParticipantToEvent(Long eventId, ParticipantToEventDto participantData) {
        Event event = eventsRepository.findById(eventId)
                .orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND, "Course with id <" + eventId + "> not found"));
        Participant participant = participantsRepository.findById(participantData.getParticipantId())
                .orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND,
                        "User with id <" + participantData.getParticipantId() + "> not found"));
        if (!participant.getEvents().add(event)) {
            throw new RestException(HttpStatus.BAD_REQUEST, "User with id <"
                    + participant.getId() + "> already in course with id <" + event.getId() + ">");
        }
        participantsRepository.save(participant);
        Set<Participant> participantOfEvent = participantsRepository.findAllByEventsContainsOrderById(event);
        return from(participantOfEvent);
    }

    @Override
    public List<ParticipantDto> getParticipantOfEvent(Long eventId) {
        Event event = getEventOrThrow(eventId);

        Set<Participant> participantOfEvent = participantsRepository.findAllByEventsContainsOrderById(event);

        return from(participantOfEvent);
    }

    private Event getEventOrThrow(Long eventId) {
        return eventsRepository.findById(eventId)
                .orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND, "Course with id <" + eventId + "> not found"));
    }
}
