package de.ait.task_05.dto;

import de.ait.task_05.models.Participant;
import de.ait.task_05.models.Place;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "Participant", description = "Описание участника события")
public class ParticipantDto {
    @Schema(description = "идентификатор участника", example = "1")
    private Long id;

    @Schema(description = "Имя", example = "Nikolay")
    private String firstName;

    @Schema(description = "Фамилия", example = "Nikolaev")
    private String lastName;

    @Schema(description = "Электронная почта", example = "Nikolaev@gmail.com")
    private String email;

    public static ParticipantDto from(Participant participant) {
        return ParticipantDto.builder()
                .id(participant.getId())
                .firstName(participant.getFirstName())
                .lastName(participant.getLastName())
                .email(participant.getEmail())
                .build();
    }

   public static List<ParticipantDto> from(List<Participant> participants) {
        return participants.stream()
                .map(ParticipantDto::from)
                .collect(Collectors.toList());
   }
}
