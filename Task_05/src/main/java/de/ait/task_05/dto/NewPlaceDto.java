package de.ait.task_05.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "Place", description = "Описание места прохождения события")
public class NewPlaceDto {
    private Long id;
    @Schema(description = "Название площадки", example = "Площадка_777")
    @NotNull
    @NotBlank
    @NotEmpty
    private String title;
    @Schema(description = "почтовый адрес", example = "Адрес_888")
    @Size(min = 5, max = 1000)
    @NotBlank
    @NotNull
    private String address;
}
