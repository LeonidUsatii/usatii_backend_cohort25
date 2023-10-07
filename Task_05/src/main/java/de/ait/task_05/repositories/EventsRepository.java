package de.ait.task_05.repositories;

import de.ait.task_05.models.Event;
import de.ait.task_05.models.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventsRepository extends JpaRepository<Event, Long> {
}
