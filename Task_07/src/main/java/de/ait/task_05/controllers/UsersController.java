package de.ait.task_05.controllers;

import de.ait.task_05.dto.NewUserDto;
import de.ait.task_05.dto.StandardResponseDto;
import de.ait.task_05.dto.UserDto;
import de.ait.task_05.security.details.AuthenticatedUser;
import de.ait.task_05.services.UsersService;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@Tags(
        @Tag(name = "Users")
)
@RestController
@RequestMapping("/api/users")
public class UsersController {

    private final UsersService usersService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity
                .ok(usersService.getAllUsers());
    }

    @Operation(summary = "Регистрация пользователя", description = "Доступно всем. По умолчанию роль - USER")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Пользователь зарегистрирован",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDto.class))),
            @ApiResponse(responseCode = "400",
                    description = "Ошибка валидации",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ValidationErrorsDto.class))),
            @ApiResponse(responseCode = "409",
                    description = "Пользователь с таким email уже есть",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StandardResponseDto.class))),
    })
    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody @Valid NewUserDto newUser) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(usersService.register(newUser));
    }

    @GetMapping("/profile")
    public ResponseEntity<UserDto> getProfile(@Parameter(hidden = true) @AuthenticationPrincipal AuthenticatedUser user) {
        Long currentUserId = user.getId();
        return ResponseEntity
                .ok(usersService.getUserById(currentUserId));
    }
}
