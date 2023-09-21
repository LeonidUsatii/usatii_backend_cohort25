package de.ait.task_04.models;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class Event {
    private Long id;
    private String title;
    private LocalDate startDate;
    private LocalDate expirationDate;
}
