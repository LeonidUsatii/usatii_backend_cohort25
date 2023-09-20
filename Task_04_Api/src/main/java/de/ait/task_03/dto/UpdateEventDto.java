package de.ait.task_03.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "UpdateEvent", description = "Информация для обновления дат")
public class UpdateEventDto {
    @Schema(description = "Дата начала")
    private LocalDate startDate;
    @Schema(description = "Дата окончания")
    private  LocalDate expirationDate;
}