package de.ait.shop.repositories.impl;


import de.ait.shop.models.Event;
import de.ait.shop.repositories.EventRepository;

import java.util.ArrayList;
import java.util.List;

public class EventsRepositoryListImpl implements EventRepository {

    private final List<Event> events = new ArrayList<>();

    private Long generatedId = 1L;

    @Override
    public List findAll() {
        return new ArrayList(events);
    }

    @Override
    public void save(Event event) {
        events.add(event);
        event.setId(generatedId);
        generatedId++;
    }

    @Override
    public Event findOneByTitle(String title) {
        return events.stream()
                .filter(event -> event.getTitle().equals(title))
                .findFirst()
                .orElse(null);
    }
}
