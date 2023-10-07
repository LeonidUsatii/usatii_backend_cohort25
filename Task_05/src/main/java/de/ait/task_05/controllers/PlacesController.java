package de.ait.task_05.controllers;

import de.ait.task_05.dto.*;
import de.ait.task_05.services.PlacesServices;
import de.ait.task_05.validation.dto.ValidationErrorsDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/places")
@Tags(value = {
        @Tag(name = "Places")
})
public class PlacesController {
    private final PlacesServices placesServices;

    @Operation(summary = "Создание площадки", description = "Доступно менеджеру по организации мероприятий")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Площадка была создана успешно",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PlaceDto.class))),
            @ApiResponse(responseCode = "400",
                    description = "Ошибка валидации",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ValidationErrorsDto.class)))
    })
    @PostMapping
    public ResponseEntity<PlaceDto> addPlace(@RequestBody @Valid NewPlaceDto newPlace) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(placesServices.addPlace(newPlace));
    }

    @Operation(summary = "Получение списка площадок", description = "Доступно всем пользователям")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Запрос обработан успешно",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PlaceDto.class))
            ),
            @ApiResponse(responseCode = "404",
                    description = "Площадка не найдена",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StandardResponseDto.class)))
    })
    @GetMapping
    public ResponseEntity<List<PlaceDto>> getPlaces() {
        return ResponseEntity
                .ok(placesServices.getPlaces());
    }

    @Operation(summary = "Получение площадки", description = "Доступно всем пользователям")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Запрос обработан успешно",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PlaceDto.class))
            ),
            @ApiResponse(responseCode = "404",
                    description = "Площадка не найдена",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StandardResponseDto.class)))
    })
    @GetMapping("/{place-id}")
    public ResponseEntity<PlaceDto> getPlace(@Parameter(description = "идентификатор площадки", example = "1")
                                               @PathVariable("place-id") Long placeId) {
        return ResponseEntity
                .ok(placesServices.getPlace(placeId));
    }


    @Operation(summary = "Добавление события на площадку", description = "Доступно менеджеру по организации мероприятий")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Событие было добавлено успешно",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PlaceDto.class))),
            @ApiResponse(responseCode = "400",
                    description = "Ошибка валидации",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ValidationErrorsDto.class)))
    })
    @PostMapping("/{place-id}/events")
    public ResponseEntity<EventDto> addEventToPlace(@PathVariable("place-id") Long placeId,
                                                    @RequestBody NewEventDto newEvent) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(placesServices.addEventToPlace(placeId, newEvent));
    }

    @Operation(summary = "Получение всех событий площадки", description = "Доступно всем пользователям")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Запрос обработан успешно",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PlaceDto.class))
            ),
            @ApiResponse(responseCode = "404",
                    description = "Площадка не найдена",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StandardResponseDto.class)))
    })

    @GetMapping("/{place-id}/events")
    public ResponseEntity<List<EventDto>> getEventsOfPlace(@PathVariable("place-id") Long placeId) {
        return ResponseEntity
                .ok(placesServices.getEventsOfPlace(placeId));
    }

}
