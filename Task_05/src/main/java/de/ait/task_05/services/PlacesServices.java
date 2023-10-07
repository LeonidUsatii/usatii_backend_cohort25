package de.ait.task_05.services;

import de.ait.task_05.dto.EventDto;
import de.ait.task_05.dto.NewEventDto;
import de.ait.task_05.dto.NewPlaceDto;
import de.ait.task_05.dto.PlaceDto;
import org.yaml.snakeyaml.events.Event;
import java.util.List;
import java.util.Set;

public interface PlacesServices {
    PlaceDto addPlace(NewPlaceDto newPlace);

    List<PlaceDto> getPlaces();

    PlaceDto getPlace(Long placeId);

    EventDto addEventToPlace(Long placeId, NewEventDto newEventDto);

    List<EventDto> getEventsOfPlace(Long placeId);



}
