package de.ait.shop.repositories.impl;
import de.ait.shop.models.Event;
import de.ait.shop.repositories.EventRepository;

import java.io.*;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class EventsRepositoryFileImpl implements EventRepository {

    private Long generatedId = 1L;
    private final String fileName;

    public EventsRepositoryFileImpl(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List findAll() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

            return reader.lines()
                    .map(line -> line.split("#"))
                    .map(parsed -> new Event(Long.parseLong(parsed[0]), parsed[1], LocalDate.parse(parsed[2]), LocalDate.parse(parsed[2])))
                    .collect(Collectors.toList());

        } catch (IOException e)  {
            throw new IllegalStateException("Problems with reading from a file: " + e.getMessage());
        }
    }

    @Override
    public void save(Event event) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {

            event.setId(generatedId);

            writer.write(event.getId() + "#" + event.getTitle() + "#" + event.getStartDate() + "#" + event.getExpirationDate());
            writer.newLine();

        } catch (IOException e) {
            throw new IllegalStateException("Problems writing to file: " + e.getMessage());
        }
        generatedId++;
    }

    @Override
    public Event findOneByTitle(String title) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

            return reader.lines()
                    .map(line -> line.split("#"))
                    .filter(parsed -> parsed[1].equals(title))
                    .findFirst()
                    .map(parsed -> new Event(Long.parseLong(parsed[0]), parsed[1], LocalDate.parse(parsed[2]), LocalDate.parse(parsed[2])))
                    .orElse(null);

        } catch (IOException e)  {
            throw new IllegalStateException("Problems with reading from a file: " + e.getMessage());
        }
    }
}
