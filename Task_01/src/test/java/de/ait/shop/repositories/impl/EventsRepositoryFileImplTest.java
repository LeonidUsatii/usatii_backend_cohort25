package de.ait.shop.repositories.impl;
import de.ait.shop.models.Event;
import org.junit.jupiter.api.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("EventsRepositoryFileImpl is works ...")
@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
public class EventsRepositoryFileImplTest {
    private static final String TEMP_EVENTS_FILE_NAME = "files/events_test.txt";

    EventsRepositoryFileImpl eventsRepository;

    @BeforeEach
    public void setUp() throws Exception {

        createNewFileForTest(TEMP_EVENTS_FILE_NAME);

        eventsRepository =  new EventsRepositoryFileImpl(TEMP_EVENTS_FILE_NAME);

    }

    @AfterEach
    public void tearDown() throws Exception {
        deleteFileAfterTest(TEMP_EVENTS_FILE_NAME);
    }

    @DisplayName("save():")
    @Nested
    class Save {

        @Test
        public void writes_correct_line_to_file() throws Exception {
            Event event = new Event("run", LocalDate.of(2023, 5, 20), LocalDate.of(2023, 6, 20));

            eventsRepository.save(event);

            String expected = "1#run#2023-05-20#2023-06-20";

            BufferedReader reader = new BufferedReader(new FileReader(TEMP_EVENTS_FILE_NAME));

            String actual = reader.readLine();

            reader.close();

            assertEquals(expected, actual);
        }
    }



    private static void createNewFileForTest(String fileName) throws IOException {

        File file = new File(fileName);

        deleteIfExists(file);

        boolean result = file.createNewFile();

        if (!result) {
            throw new IllegalStateException("Problems with file create");
        }
    }

    private static void deleteFileAfterTest(String fileName) {
        File file = new File(fileName);

        deleteIfExists(file);
    }


    private static void deleteIfExists(File file) {
        if (file.exists()) {

            boolean result = file.delete();

            if (!result) {
                throw new IllegalStateException("Problems with file delete");
            }
        }
    }
}
