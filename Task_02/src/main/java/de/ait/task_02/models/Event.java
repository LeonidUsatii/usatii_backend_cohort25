package de.ait.task_02.models;

import java.time.LocalDate;
import java.util.Objects;

public class Event {

    private String title;
    private LocalDate startDate;
    private LocalDate expirationDate;

    public Event() {
    }
    public Event(String title, LocalDate startDate, LocalDate expirationDate) {
        this.title = title;
        this.startDate = startDate;
        this.expirationDate = expirationDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    @Override
    public String toString() {
        return "Event{" +
                ", title='" + title + '\'' +
                ", startDate=" + startDate +
                ", expirationDate=" + expirationDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(title, event.title) && Objects.equals(startDate, event.startDate) && Objects.equals(expirationDate, event.expirationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, startDate, expirationDate);
    }
}
