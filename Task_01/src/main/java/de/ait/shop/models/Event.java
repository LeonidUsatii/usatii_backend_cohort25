package de.ait.shop.models;

import java.time.LocalDate;

public class Event {
    private Long id;
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

    public Event(Long id, String title, LocalDate startDate, LocalDate expirationDate) {
        this.id = id;
        this.title = title;
        this.startDate = startDate;
        this.expirationDate = expirationDate;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getId() {
        return id;
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
                "id=" + id +
                ", title='" + title + '\'' +
                ", startDate=" + startDate +
                ", expirationDate=" + expirationDate +
                '}';
    }
}
