package de.ait.task_04.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "UpdateEvent", description = "Информация для обновления дат")
public class UpdateEventDto {
    @Schema(description = "Дата начала")
    private String startDate;
    @Schema(description = "Дата окончания")
    private  String expirationDate;
}
