package de.ait.task_05.services;

import de.ait.task_05.dto.NewUserDto;
import de.ait.task_05.dto.UserDto;

public interface UsersService {
    UserDto register(NewUserDto newUser);
}
