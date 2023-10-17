package de.ait.task_05.dto;

import de.ait.task_05.models.Place;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "Place", description = "Описание места прохождения события")
public class PlaceDto {
    private Long id;
    @Schema(description = "Название площадки", example = "Площадка_777")
    private String title;
    @Schema(description = "почтовый адрес", example = "Адрес_888")
    private String address;

    public static PlaceDto from(Place place) {
        return PlaceDto.builder()
                .id(place.getId())
                .title(place.getTitle())
                .address(place.getAddress())
                .build();
    }

    public static List<PlaceDto> from(List<Place> places) {
        return places.stream()
                .map(PlaceDto::from)
                .collect(Collectors.toList());
    }
}
