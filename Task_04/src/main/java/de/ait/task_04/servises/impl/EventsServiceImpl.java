package de.ait.task_04.servises.impl;



import de.ait.task_04.dto.EventDto;
import de.ait.task_04.dto.NewEventDto;
import de.ait.task_04.dto.UpdateEventDto;
import de.ait.task_04.models.Event;
import de.ait.task_04.repositories.EventRepository;
import de.ait.task_04.servises.EventsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static de.ait.task_04.dto.EventDto.from;


@RequiredArgsConstructor
@Service
public class EventsServiceImpl implements EventsService {

    private final EventRepository eventRepository;

    @Override
    public EventDto addEvent(NewEventDto newEvent) {
//        boolean isCorrectSpacing = (LocalDate.parse(newEvent.getExpirationDate())
//                .isAfter(LocalDate.parse(newEvent.getStartDate())));
//        if (newEvent.getTitle() == null || newEvent.getTitle().equals("")
//                || newEvent.getTitle().equals(" ")) {
//            throw new IllegalArgumentException("Title cannot be empty");
//        }
//        if (!isCorrectSpacing) {
//            throw new IllegalArgumentException("Enter the correct dates");
//        }
        Event existedEvent = eventRepository.findOneByTitle(newEvent.getTitle());

        if (existedEvent != null) {
            throw new IllegalArgumentException("There is already an event with the same name");
        }
        Event event = Event.builder()
                .title(newEvent.getTitle())
                .startDate(LocalDate.parse(newEvent.getStartDate()))
                .expirationDate(LocalDate.parse(newEvent.getExpirationDate()))
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
        eventForUpdate.setStartDate(LocalDate.parse(updateEvent.getStartDate()));
        eventForUpdate.setExpirationDate(LocalDate.parse(updateEvent.getExpirationDate()));
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
