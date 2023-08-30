package de.ait.shop.controllers;


import de.ait.shop.models.Event;
import de.ait.shop.services.EventsService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class EventsController {

    private final Scanner scanner;
    private final EventsService eventsService;

    public EventsController(Scanner scanner, EventsService eventsService) {
        this.scanner = scanner;
        this.eventsService = eventsService;
    }

    public void addEvent() {
        System.out.println("Enter the name of the event:");
        String title = scanner.nextLine();

        System.out.println("Enter the start date:");
        LocalDate startDate = LocalDate.of(scanner.nextInt(), scanner.nextInt(), scanner.nextInt());

        System.out.println("Enter the expiration date:");
        LocalDate expirationDate = LocalDate.of(scanner.nextInt(), scanner.nextInt(), scanner.nextInt());

        eventsService.addEvent(title, startDate, expirationDate);
        scanner.nextLine();
    }

    public void getAllEvents() {
        List<Event> events = eventsService.getAllEvents();
        System.out.println(events);
    }
}
