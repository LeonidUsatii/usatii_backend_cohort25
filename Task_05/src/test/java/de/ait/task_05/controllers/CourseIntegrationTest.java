package de.ait.task_05.controllers;

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
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DisplayName("Endpoint /users is works:")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CourseIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Nested
    @DisplayName("GET /courses")
    public class GetCourses {

        @Test
        @Sql(scripts = {"/sql/schema.sql", "/sql/data.sql"})
        public void return_list_of_courses() throws Exception {
            mockMvc.perform(get("/api/courses"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.size()", is(5)))
                    .andExpect(jsonPath("$.[0].id", is(1)))
                    .andExpect(jsonPath("$.[1].beginDate", is("2023-09-13")))
                    .andExpect(jsonPath("$.[2].id", is(3)))
                    .andExpect(jsonPath("$.[3].id", is(4)))
                    .andExpect(jsonPath("$.[3].endDate", is("2024-09-15")))
                    .andExpect(jsonPath("$.[4].id", is(5)));
        }
    }

    @Nested
    @DisplayName("POST /courses")
    public class PostCourses {

        @Test
        @Sql(scripts = {"/sql/schema.sql", "/sql/data.sql"})
        public void return_created_user() throws Exception {
            mockMvc.perform(post("/api/courses")
                            .contentType("application/json")
                            .content("{\n" +
                                    "  \"beginDate\": \"2023-09-13\",\n" +
                                    "  \"description\": \"Описание курса 6\",\n" +
                                    "  \"endDate\": \"2023-09-23\",\n" +
                                    "  \"price\": \"600\",\n" +
                                    "  \"state\": \"DRAFT\",\n" +
                                    "  \"title\": \"Course_6\"\n" +
                                    "}"))
                    .andExpect(jsonPath("$.id", is(6)))
                    .andExpect(status().isCreated());
        }
    }
}