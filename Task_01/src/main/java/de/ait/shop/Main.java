package de.ait.shop;


import de.ait.shop.controllers.EventsController;

import de.ait.shop.repositories.EventRepository;
import de.ait.shop.repositories.impl.EventsRepositoryFileImpl;
import de.ait.shop.repositories.impl.EventsRepositoryListImpl;
import de.ait.shop.services.EventsService;
import de.ait.shop.services.impl.EventsServiceImpl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EventRepository eventRepositoryList = new EventsRepositoryListImpl();
        EventRepository eventRepositoryFile = new EventsRepositoryFileImpl("files/events.txt");
        EventsService eventsService = new EventsServiceImpl(eventRepositoryFile);
        EventsController eventsController = new EventsController(scanner, eventsService);

        boolean isRun = true;

        while (isRun) {
            System.out.println("Enter the command:");
            String command = scanner.nextLine();

            switch (command) {
                case "/addEvent" ->
                        eventsController.addEvent();
                case "/events" ->
                        eventsController.getAllEvents();
                case "/exit" -> isRun = false;
            }
        }
    }

}
