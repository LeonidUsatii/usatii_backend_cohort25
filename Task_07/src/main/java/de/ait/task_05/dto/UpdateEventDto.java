package de.ait.task_05.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
@Data
public class UpdateEventDto {
    @Schema(description = "Поля для обновления, null-значения сохраняются в базе")

    @NotBlank
    @NotEmpty
    private  String title;

    @Schema(description = "Дата начала  события", example = "2023-02-02")
    @Pattern(regexp = "^(?:(?:19|20)\\d\\d)-(?:0[1-9]|1[0-2])-(?:0[1-9]|1\\d|2\\d|3[0-1])$")
    private String startDate;

    @Schema(description = "Дата окончания  события", example = "2023-04-02")
    @Pattern(regexp = "^(?:(?:19|20)\\d\\d)-(?:0[1-9]|1[0-2])-(?:0[1-9]|1\\d|2\\d|3[0-1])$")
    private String expirationDate;

    @Schema(description = "статус события - EXPECTATION, STARTED, COMPLETED", example = "STARTED")
    @NotBlank
    @NotEmpty
    private String state;
}
