package de.ait.task_03.repositories;
import de.ait.task_03.models.Event;

public interface EventRepository extends CrudRepository<Event>{

    Event findOneByTitle(String title);

}
