package de.ait.task_05.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "Participant", description = "Описание участника события")
public class NewParticipantDto {
    @Schema(description = "Имя", example = "Nikolay")
    @NotNull
    @NotBlank
    @NotEmpty
    private String firstName;
    @Schema(description = "Фамилия", example = "Nikolaev")
    @NotNull
    @NotBlank
    @NotEmpty
    private String lastName;
    @Schema(description = "Электронная почта", example = "Nikolaev@gmail.com")
    @Email
    private String email;

}
