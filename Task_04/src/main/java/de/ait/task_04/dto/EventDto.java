package de.ait.task_04.dto;

import de.ait.task_04.models.Event;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "Event", description = "Информация о делах")
public class EventDto {
    @Schema(description = "ID")
    private Long id;
    @Schema(description = "Название события", example = "День ....")
    private  String title;
    @Schema(description = "Дата начала")
    private String startDate;
    @Schema(description = "Дата окончания")
    private  String expirationDate;

    public static EventDto from(Event event) {
        return EventDto.builder()
                .id(event.getId())
                .title(event.getTitle())
                .startDate(event.getStartDate().toString())
                .expirationDate(event.getExpirationDate().toString())
                .build();
    }

    public static List<EventDto> from(List<Event> events) {
//        List<EventDto> eventDtos = new ArrayList<>();
//
//        for (Event event : events) {
//            EventDto eventDto = from(event);
//            eventDtos.add(eventDto);
//        }
//
//        return eventDtos;

        return events.stream()
                .map(EventDto::from)
                .collect(Collectors.toList());

    }


    @Override
    public String toString() {
        return "EventDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", startDate=" + startDate +
                ", expirationDate=" + expirationDate +
                '}';
    }
}
