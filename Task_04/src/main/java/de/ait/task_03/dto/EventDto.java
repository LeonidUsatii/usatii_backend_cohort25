package de.ait.task_03.dto;

import de.ait.task_03.models.Event;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Event", description = "Информация о делах")
public class EventDto {
    @Schema(description = "ID")
    private Long id;
    @Schema(description = "Название события", example = "День ....")
    private  String title;
    @Schema(description = "Дата начала")
    private  LocalDate startDate;
    @Schema(description = "Дата окончания")
    private  LocalDate expirationDate;

    public static EventDto from(Event event) {
        return new EventDto(event.getId(), event.getTitle(), event.getStartDate(), event.getExpirationDate());
    }

    public static List<EventDto> from(List<Event> events) {
//        List<UserDto> userDtos = new ArrayList<>();
//
//        for (User user : users) {
//            UserDto userDto = from(user);
//            userDtos.add(userDto);
//        }
//
//        return userDtos;

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







