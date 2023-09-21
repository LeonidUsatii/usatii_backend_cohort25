package de.ait.task_04.repositories.impl;

import de.ait.task_04.models.Event;
import de.ait.task_04.repositories.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



@RequiredArgsConstructor
@Repository
public class EventsRepositoryJdbcImpl implements EventRepository {
    //language=SQL

    private static final String SQL_SELECT_BY_ID = "select * from event where id = ?";
    //language=SQL
    private static final String SQL_SELECT_ALL = "select * from event";

    //language=SQL
    private static final String SQL_UPDATE_BY_ID = "update event set start_date = ?, expiration_date = ? where id = ?";

    //language=SQL
    private static final String SQL_DELETE_EVENT_BY_ID = "delete from event where id = ?";

    private final DataSource dataSource;

    private final JdbcTemplate jdbcTemplate;

    private static final RowMapper<Event> EVENT_ROW_MAPPER = (row, rowNumber) -> {

        Long id = row.getLong("id");
        String title = row.getString("title");
        String startDate = row.getString("start_date");
        String expirationDate= row.getString("expiration_date");

        return Event.builder()
                .id(id)
                .title(title)
                .startDate(LocalDate.parse(startDate))
                .expirationDate(LocalDate.parse(expirationDate))
                .build();
    };


    @Override
    public List<Event> findAll() {

        return jdbcTemplate.query(SQL_SELECT_ALL, EVENT_ROW_MAPPER);
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
    public Event findById(Long id) {

        return jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, EVENT_ROW_MAPPER, id);
    }

    @Override
    public void deleteById(Long id) {
        jdbcTemplate.update(SQL_DELETE_EVENT_BY_ID, id);
    }

    @Override
    public void update(Event model) {

        jdbcTemplate.update(SQL_UPDATE_BY_ID, model.getStartDate(), model.getExpirationDate(), model.getId());

    }

    @Override
    public Event findOneByTitle(String title) {
        return null;
    }

}
