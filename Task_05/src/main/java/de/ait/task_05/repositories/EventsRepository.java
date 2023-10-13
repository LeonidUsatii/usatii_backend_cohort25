package de.ait.task_05.repositories;

import de.ait.task_05.models.Event;
import de.ait.task_05.models.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface EventsRepository extends JpaRepository<Event, Long> {
    Set<Event> findAllByPlaceOrderById(Place place);

    Optional<Event> findByPlaceAndId(Place place, Long eventId);
}
