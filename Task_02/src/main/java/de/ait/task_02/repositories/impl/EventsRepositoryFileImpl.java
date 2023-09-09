package de.ait.task_02.repositories.impl;
import de.ait.task_02.models.Event;
import de.ait.task_02.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EventsRepositoryFileImpl implements EventRepository {
    private final String fileName;

    public EventsRepositoryFileImpl(@Value("${events.file-name}") String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List findAll() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

            return reader.lines()
                    .map(line -> line.split("#"))
                    .map(parsed -> new Event(parsed[0], LocalDate.parse(parsed[1]), LocalDate.parse(parsed[2])))
                    .collect(Collectors.toList());

        } catch (IOException e)  {
            throw new IllegalStateException("Problems with reading from a file: " + e.getMessage());
        }
    }

    @Override
    public void save(Event event) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {

            writer.write(event.getTitle() + "#" + event.getStartDate() + "#" + event.getExpirationDate());
            writer.newLine();

        } catch (IOException e) {
            throw new IllegalStateException("Problems writing to file: " + e.getMessage());
        }

    }

    @Override
    public Event findOneByTitle(String title) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

            return reader.lines()
                    .map(line -> line.split("#"))
                    .filter(parsed -> parsed[1].equals(title))
                    .findFirst()
                    .map(parsed -> new Event(parsed[0], LocalDate.parse(parsed[1]), LocalDate.parse(parsed[2])))
                    .orElse(null);

        } catch (IOException e)  {
            throw new IllegalStateException("Problems with reading from a file: " + e.getMessage());
        }
    }
}
