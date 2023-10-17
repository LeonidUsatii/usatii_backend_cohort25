package de.ait.task_05.services;

import de.ait.task_05.dto.NewUserDto;
import de.ait.task_05.dto.UserDto;

import java.util.List;

public interface UsersService {
    UserDto register(NewUserDto newUser);

    UserDto getUserById(Long currentUserId);

    List<UserDto> getAllUsers();
}
