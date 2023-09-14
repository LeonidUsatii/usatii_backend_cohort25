package de.ait.task_03.services.impl;

import de.ait.task_03.dto.EventDto;
import de.ait.task_03.models.Event;
import de.ait.task_03.repositories.EventRepository;
import de.ait.task_03.services.EventsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static de.ait.task_03.dto.EventDto.from;

@Service
public class EventsServiceImpl implements EventsService {

    private final EventRepository eventRepository;

    public EventsServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public EventDto addEvent(EventDto eventDto) {
        boolean isCorrectSpacing = (eventDto.getExpirationDate()
                .isAfter(eventDto.getStartDate()));
        if (eventDto.getTitle() == null || eventDto.getTitle().equals("")
                || eventDto.getTitle().equals(" ")) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        if (!isCorrectSpacing) {
            throw new IllegalArgumentException("Enter the correct dates");
        }
        Event existedEvent = eventRepository.findOneByTitle(eventDto.getTitle());

        if (existedEvent != null) {
            throw new IllegalArgumentException("There is already an event with the same name");
        }
        Event event = new Event(eventDto.getTitle(), eventDto.getStartDate(),
                eventDto.getExpirationDate());
        eventRepository.save(event);
        return from(event);
    }

    @Override
    public List<EventDto> getAllEvents() {

        List<Event>  events = eventRepository.findAll();
        List<EventDto> eventDtos = new ArrayList<>();

        for (Event event : events) {
            EventDto eventDto = new EventDto(event.getTitle(), event.getStartDate(),
                    event.getExpirationDate());
            eventDtos.add(eventDto);
        }
        return eventDtos;
    }
}
