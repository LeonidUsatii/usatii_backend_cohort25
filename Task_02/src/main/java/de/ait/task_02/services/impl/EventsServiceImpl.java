package de.ait.task_02.services.impl;

import de.ait.task_02.models.Event;
import de.ait.task_02.repositories.EventRepository;
import de.ait.task_02.services.EventsService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EventsServiceImpl implements EventsService {

    private final EventRepository eventRepository;

    public EventsServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Event addEvent(String title, LocalDate startDate, LocalDate expirationDate) {
        boolean isCorrectSpacing = (expirationDate.isAfter(startDate));
        if (title == null || title.equals("") || title.equals(" ")) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        if (!isCorrectSpacing) {
            throw new IllegalArgumentException("Enter the correct dates");
        }
        Event existedEvent = eventRepository.findOneByTitle(title);

        if (existedEvent != null) {
            throw new IllegalArgumentException("There is already an event with the same name");
        }
        Event event = new Event(title, startDate, expirationDate);
        eventRepository.save(event);
        return event;
    }

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }
}
