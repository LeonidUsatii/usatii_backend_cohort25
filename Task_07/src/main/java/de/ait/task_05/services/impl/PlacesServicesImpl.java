package de.ait.task_05.services.impl;

import de.ait.task_05.dto.*;
import de.ait.task_05.exceptions.RestException;
import de.ait.task_05.models.Event;
import de.ait.task_05.models.Place;
import de.ait.task_05.repositories.EventsRepository;
import de.ait.task_05.repositories.PlacesRepository;
import de.ait.task_05.services.PlacesServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static de.ait.task_05.dto.PlaceDto.from;
import static de.ait.task_05.dto.EventDto.from;
@RequiredArgsConstructor
@Service
public class PlacesServicesImpl implements PlacesServices {
    private final PlacesRepository placesRepository;
    private final EventsRepository eventsRepository;

    @Override
    public PlaceDto addPlace(NewPlaceDto newPlace) {
        Place place = Place.builder()
                .title(newPlace.getTitle())
                .address(newPlace.getAddress())
                .build();
        placesRepository.save(place);
        return from(place);
    }

    @Override
    public List<PlaceDto> getPlaces() {
        List<Place> places = placesRepository.findAll();
        return from(places);
    }

    @Override
    public PlaceDto getPlace(Long placeId) {
        Place place = placesRepository.findById(placeId)
                .orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND, "Place with id <" + placeId + "> not found"));
        return from(place);
    }

    @Override
    public EventDto addEventToPlace(Long placeId, NewEventDto newEvent) {
        Place place = getPlaceOrThrow(placeId);
        Event event;
        if (newEvent.getExistsEventId() == null) {
            event = Event.builder()
                    .title(newEvent.getTitle())
                    .state(Event.State.valueOf(newEvent.getState()))
                    .startDate(LocalDate.parse(newEvent.getStartDate()))
                    .expirationDate(LocalDate.parse(newEvent.getExpirationDate()))
                    .place(place)
                    .build();
        }   else {
            event = eventsRepository.findById(newEvent.getExistsEventId())
                    .orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND,
                            "Lesson with id <" + newEvent.getExistsEventId() + "> not found"));
            event.setPlace(place);
        }
        eventsRepository.save(event);
        return from(event);
    }

    @Override
    public List<EventDto> getEventsOfPlace(Long placeId) {
        Place place = placesRepository.findById(placeId)
                .orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND, "Place with id <" + placeId + "> not found"));
        Set<Event> events = eventsRepository.findAllByPlaceOrderById(place);
        return from(events);
    }

    @Override
    public EventDto deleteEventFromPlace(Long placeId, Long eventId) {
        Place place = getPlaceOrThrow(placeId);
        Event event = eventsRepository.findByPlaceAndId(place, eventId)
                .orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND,
                        "Lesson with id <" + eventId + "> not found in course with id <" + placeId + ">"));
        event.setPlace(null);
        eventsRepository.save(event);
        return from(event);
    }

    @Override
    public EventDto updateEventInPlace(Long placeId, Long eventId, UpdateEventDto updateEvent) {
        Place place = getPlaceOrThrow(placeId);
        Set<Event> eventOfPlace = place.getEvents();
        Event event = eventsRepository.findByPlaceAndId(place, eventId)
                .orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND,
                        "Lesson with id <" + eventId + "> not found in course with id <" + placeId + ">"));
        event.setTitle(updateEvent.getTitle());
        event.setStartDate(LocalDate.parse(updateEvent.getStartDate()));
        event.setExpirationDate(LocalDate.parse(updateEvent.getExpirationDate()));
        event.setState(Event.State.valueOf(updateEvent.getState()));
        eventsRepository.save(event);
        return from(event);
    }

    private Place getPlaceOrThrow(Long placeId) {
        return placesRepository.findById(placeId)
                .orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND, "Course with id <" + placeId + "> not found"));
    }

}
