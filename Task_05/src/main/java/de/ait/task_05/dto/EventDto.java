package de.ait.task_05.dto;

import de.ait.task_05.models.Event;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "Event", description = "Описание события")

public class EventDto {
    @Schema(description = "идентификатор события", example = "1")
    private Long id;
    @Schema(description = "Название  события", example = "День рождения ...")
    private String title;
    @Schema(description = "Дата начала  события", example = "2023-02-02")
    private String startDate;
    @Schema(description = "Дата окончания  события", example = "2023-04-02")
    private String expirationDate;
    @Schema(description = "статус события - EXPECTATION, STARTED, COMPLETED", example = "STARTED")
    private String state;

    public static EventDto from(Event event) {
        return EventDto.builder()
                .id(event.getId())
                .title(event.getTitle())
                .startDate(event.getStartDate().toString())
                .expirationDate(event.getExpirationDate().toString())
                .state(event.getState().toString())
                .build();
    }

    public static List<EventDto> from(Set<Event> events) {

        return events.stream()
                .map(EventDto::from)
                .collect(Collectors.toList());
    }
}
