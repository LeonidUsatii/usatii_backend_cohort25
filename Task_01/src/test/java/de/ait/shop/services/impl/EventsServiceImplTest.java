package de.ait.shop.services.impl;


import de.ait.shop.repositories.EventRepository;
import de.ait.shop.models.Event;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@DisplayName("UsersServiceImpl is works ...")
@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
class EventsServiceImplTest {

    private static final String EXIST_EVENT_TITLE = "run";
    private static final String NOT_EXIST_EVENT_TITLE = "meeting";
    private static final LocalDate DEFAULT_DATE_START = LocalDate.of(2023, 5, 20);
    private static final LocalDate DEFAULT_DATE_EXPIRATION = LocalDate.of(2023, 6, 20);
    private static final Event NOT_EXIST_EVENT = new Event(NOT_EXIST_EVENT_TITLE, DEFAULT_DATE_START, DEFAULT_DATE_EXPIRATION);
    private static final Event EXIST_EVENT = new Event(EXIST_EVENT_TITLE, DEFAULT_DATE_START, DEFAULT_DATE_EXPIRATION);


    private EventsServiceImpl eventsService;

    private EventRepository eventsRepository;

    @BeforeEach
    public void setUp() {
        eventsRepository = Mockito.mock(EventRepository.class);
        when(eventsRepository.findOneByTitle(EXIST_EVENT_TITLE)).thenReturn(EXIST_EVENT);
        when(eventsRepository.findOneByTitle(NOT_EXIST_EVENT_TITLE)).thenReturn(null);
        this.eventsService = new EventsServiceImpl(eventsRepository);
    }

    @Nested
    @DisplayName(("addEvent():"))
    class AddEvent {
        @Test
        public void on_incorrect_title_throws_exception() {
            assertThrows(IllegalArgumentException.class, () -> eventsService.addEvent(null, DEFAULT_DATE_START,
                    DEFAULT_DATE_EXPIRATION));
        }

        @Test
        public void on_incorrect_date_throws_exception() {

            assertThrows(IllegalArgumentException.class, () -> eventsService.addEvent("run", LocalDate.of(2023, 6, 20),
                    LocalDate.of(2023, 5, 20)));
        }

        @Test
        public void on_existed_user_throws_exception() {
            assertThrows(IllegalArgumentException.class, () -> eventsService.addEvent(EXIST_EVENT_TITLE, DEFAULT_DATE_START,
                    DEFAULT_DATE_EXPIRATION));
        }

        @Test
        public void returns_created_event() {
            Event actual = eventsService.addEvent(NOT_EXIST_EVENT_TITLE, DEFAULT_DATE_START,
                    DEFAULT_DATE_EXPIRATION);
            вы
            assertNotNull(actual);
        }

    }


}