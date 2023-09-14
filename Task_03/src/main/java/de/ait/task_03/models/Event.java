package de.ait.task_03.models;

import lombok.*;

import java.time.LocalDate;
import java.util.Objects;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Event {
    private String title;
    private LocalDate startDate;
    private LocalDate expirationDate;

}