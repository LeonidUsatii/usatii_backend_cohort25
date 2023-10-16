package de.ait.task_05.services;

import de.ait.task_05.dto.*;
import org.yaml.snakeyaml.events.Event;
import java.util.List;
import java.util.Set;

public interface PlacesServices {
    PlaceDto addPlace(NewPlaceDto newPlace);

    List<PlaceDto> getPlaces();

    PlaceDto getPlace(Long placeId);

    EventDto addEventToPlace(Long placeId, NewEventDto newEventDto);

    List<EventDto> getEventsOfPlace(Long placeId);


    EventDto deleteEventFromPlace(Long placeId, Long eventId);

    EventDto updateEventInPlace(Long placeId, Long eventId, UpdateEventDto updateEvent);
}
