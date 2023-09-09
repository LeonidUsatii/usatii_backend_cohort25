package de.ait.task_02.repositories;
import de.ait.task_02.models.Event;

public interface EventRepository extends CrudRepository<Event>{

    Event findOneByTitle(String title);

}
