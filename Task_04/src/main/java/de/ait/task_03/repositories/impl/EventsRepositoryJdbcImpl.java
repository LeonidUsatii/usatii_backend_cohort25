package de.ait.task_03.repositories.impl;

import de.ait.task_03.models.Event;
import de.ait.task_03.repositories.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Repository
public class EventsRepositoryJdbcImpl implements EventRepository {
    private final DataSource dataSource;
    @Override
    public List<Event> findAll() {
        return null;
    }

    @Override
    public void save(Event model) {

        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(dataSource)
                .usingGeneratedKeyColumns("id");
        jdbcInsert.withTableName("event");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("title", model.getTitle());
        parameters.put("start_date", model.getStartDate());
        parameters.put("expiration_date", model.getExpirationDate());

        long generatedId = jdbcInsert.executeAndReturnKey(parameters).longValue();
        model.setId(generatedId);
    }

    @Override
    public Event findOneByTitle(String title) {
        return null;
    }
}
