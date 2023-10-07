package de.ait.task_05.repositories;

import de.ait.task_05.models.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlacesRepository extends JpaRepository<Place, Long> {
}
