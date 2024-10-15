package co.edu.udea.vuelosback.application;

import co.edu.udea.vuelosback.core.dao.UserRepository;
import co.edu.udea.vuelosback.core.dto.UserResponseDto;
import co.edu.udea.vuelosback.core.interfaces.IUserService;
import co.edu.udea.vuelosback.core.models.User;
import graphql.GraphQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserResponseDto getUserById(UUID id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new GraphQLException("User not found");
        }
        return new UserResponseDto(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getAplicationRole().getRol()
        );
    }
}
