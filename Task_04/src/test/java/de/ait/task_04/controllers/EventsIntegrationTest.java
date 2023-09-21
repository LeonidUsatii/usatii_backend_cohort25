package de.ait.task_04.controllers;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DisplayName("Endpoint /users is works:")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class EventsIntegrationTest {

    @Autowired
    private MockMvc mockMvc;



    @Nested
    @DisplayName("GET /events")
    public class GetUsers {

        @Test
        @Sql(scripts = {"/sql/schema.sql", "/sql/data.sql"})
        public void return_list_of_events() throws Exception {
            mockMvc.perform(get("/api/events"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.size()", is(5)))
                    .andExpect(jsonPath("$.[0].id", is(1)))
                    .andExpect(jsonPath("$.[1].startDate", is("2023-09-14")))
                    .andExpect(jsonPath("$.[2].id", is(3)))
                    .andExpect(jsonPath("$.[3].id", is(4)))
                    .andExpect(jsonPath("$.[3].expirationDate", is("2023-09-18")))
                    .andExpect(jsonPath("$.[4].id", is(5)));
        }
    }

    @Nested
    @DisplayName("POST /events")
    public class PostUsers {

        @Test
        @Sql(scripts = {"/sql/schema.sql", "/sql/data.sql"})
        public void return_created_user() throws Exception {
            mockMvc.perform(post("/api/events")
                            .contentType("application/json")
                            .content("{\n" +
                                    "  \"title\": \"Home Work5\",\n" +
                                    "  \"startDate\": \"2023-09-20\",\n" +
                                    "  \"expirationDate\": \"2023-09-23\"\n" +
                                    "}"))
                    .andExpect(jsonPath("$.id", is(6)))
                    .andExpect(status().isCreated());
        }
    }
}