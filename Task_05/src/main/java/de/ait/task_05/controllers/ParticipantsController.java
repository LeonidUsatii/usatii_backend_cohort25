package de.ait.task_05.controllers;

import de.ait.task_05.dto.NewParticipantDto;
import de.ait.task_05.dto.NewPlaceDto;
import de.ait.task_05.dto.ParticipantDto;
import de.ait.task_05.dto.PlaceDto;
import de.ait.task_05.models.Participant;
import de.ait.task_05.services.ParticipantsServices;
import de.ait.task_05.validation.dto.ValidationErrorsDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/participants")
@Tags(value = {
        @Tag(name = "Participants")
})
public class ParticipantsController {
   private  final ParticipantsServices participantsServices;
    @Operation(summary = "Добавление участника", description = "Доступно менеджеру по организации мероприятий")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Участник добавлен успешно",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PlaceDto.class))),
            @ApiResponse(responseCode = "400",
                    description = "Ошибка валидации",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ValidationErrorsDto.class)))
    })
    @PostMapping
    public ResponseEntity<ParticipantDto> addParticipant(@RequestBody @Valid NewParticipantDto newParticipant) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(participantsServices.addParticipant(newParticipant));
    }
}
