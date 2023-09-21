package de.ait.task_04.repositories;
import de.ait.task_04.models.Event;

public interface EventRepository extends CrudRepository<Event>{
    Event findOneByTitle(String title);
}
