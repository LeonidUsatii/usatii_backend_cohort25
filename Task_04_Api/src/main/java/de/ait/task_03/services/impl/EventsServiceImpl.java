package de.ait.task_03.services.impl;

import de.ait.task_03.dto.EventDto;
import de.ait.task_03.dto.NewEventDto;
import de.ait.task_03.dto.UpdateEventDto;
import de.ait.task_03.models.Event;
import de.ait.task_03.repositories.EventRepository;
import de.ait.task_03.services.EventsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static de.ait.task_03.dto.EventDto.from;
@RequiredArgsConstructor
@Service
public class EventsServiceImpl implements EventsService {

    private final EventRepository eventRepository;

//    public EventsServiceImpl(EventRepository eventRepository) {
//
//        this.eventRepository = eventRepository;
//    }

    @Override
    public EventDto addEvent(NewEventDto newEvent) {
        boolean isCorrectSpacing = (newEvent.getExpirationDate()
                .isAfter(newEvent.getStartDate()));
        if (newEvent.getTitle() == null || newEvent.getTitle().equals("")
                || newEvent.getTitle().equals(" ")) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        if (!isCorrectSpacing) {
            throw new IllegalArgumentException("Enter the correct dates");
        }
        Event existedEvent = eventRepository.findOneByTitle(newEvent.getTitle());

        if (existedEvent != null) {
            throw new IllegalArgumentException("There is already an event with the same name");
        }
        Event event = Event.builder()
                .title(newEvent.getTitle())
                .startDate(newEvent.getStartDate())
                .expirationDate(newEvent.getExpirationDate())
                .build();

        eventRepository.save(event);
        return from(event);
    }

    @Override
    public List<EventDto> getAllEvents() {
        return from(eventRepository.findAll());
    }

    @Override
    public EventDto getEvent(Long id) {
        return from(eventRepository.findById(id));
    }

    @Override
    public EventDto updateEvent(Long id, UpdateEventDto updateEvent) {
        Event eventForUpdate = eventRepository.findById(id);
        eventForUpdate.setStartDate(updateEvent.getStartDate());
        eventForUpdate.setExpirationDate(updateEvent.getExpirationDate());
        eventRepository.update(eventForUpdate);

        return from(eventForUpdate);
    }

    @Override
    public EventDto deleteEvent(Long id) {
        Event forDelete = eventRepository.findById(id);
        eventRepository.deleteById(id);

        return from(forDelete);
    }
}
