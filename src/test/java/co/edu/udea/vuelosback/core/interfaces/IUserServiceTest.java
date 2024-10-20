package co.edu.udea.vuelosback.core.interfaces;

import co.edu.udea.vuelosback.core.dao.UserRepository;
import co.edu.udea.vuelosback.core.dto.UserResponseDto;
import co.edu.udea.vuelosback.core.models.AplicationRole;
import co.edu.udea.vuelosback.core.models.User;
import graphql.GraphQLException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class IUserServiceTest {

    UUID id = UUID.randomUUID();
    @Autowired
    private IUserService userService;
    @MockBean
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        User user = User.builder()
                .id(this.id)
                .email("jhonas@udea.edu.co")
                .password("123")
                .aplicationRole(AplicationRole.usuario)
                .phoneNumber("3111111111")
                .fullName("Jhonatan Granda")
                .build();

        Mockito.when(userRepository.findById(this.id)).thenReturn(Optional.of(user));
    }



    @Test
    void getUserByIdFound() {
        UUID id = this.id;
        UserResponseDto user = userService.getUserById(id);
        assertEquals(id, user.getId());
    }

    @Test
    void getUserByIdNotFound() {
        UUID id = UUID.randomUUID();
        GraphQLException exception = assertThrows(GraphQLException.class, () -> userService.getUserById(id));
        String expectedMessage = "User not found";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }
}