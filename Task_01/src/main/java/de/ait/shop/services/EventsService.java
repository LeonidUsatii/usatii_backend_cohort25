package de.ait.shop.services;



import de.ait.shop.models.Event;

import java.time.LocalDate;
import java.util.List;

public interface EventsService {
    Event addEvent(String title, LocalDate startDate, LocalDate expirationDate);

    List<Event> getAllEvents();
}
