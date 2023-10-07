package de.ait.task_05.services.impl;

import de.ait.task_05.dto.EventDto;
import de.ait.task_05.dto.NewEventDto;
import de.ait.task_05.dto.NewPlaceDto;
import de.ait.task_05.dto.PlaceDto;
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
        Place place = placesRepository.findById(placeId)
                .orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND, "Place with id <" + placeId + "> not found"));
        Event event = Event.builder()
                .title(newEvent.getTitle())
                .state(Event.State.valueOf(newEvent.getState()))
                .startDate(LocalDate.parse(newEvent.getStartDate()))
                .expirationDate(LocalDate.parse(newEvent.getExpirationDate()))
                .place(place)
                .build();
       eventsRepository.save(event);
       return from(event);
    }

    @Override
    public List<EventDto> getEventsOfPlace(Long placeId) {
        Place place = placesRepository.findById(placeId)
                .orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND, "Place with id <" + placeId + "> not found"));

       Set<Event> events = place.getEvents();
        return from(events);
    }

}
