package co.edu.udea.vuelosback.core.interfaces;

import co.edu.udea.vuelosback.core.dto.UserResponseDto;

import java.util.UUID;

public interface IUserService {
    UserResponseDto getUserById(UUID id);
}
