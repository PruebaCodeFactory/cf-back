package co.edu.udea.vuelosback.core.interfaces;

import co.edu.udea.vuelosback.core.dao.UserRepository;
import co.edu.udea.vuelosback.core.dto.LoginRequestDto;
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
class IAuthServiceTest {

    UUID id = UUID.randomUUID();

    @Autowired
    IAuthService authService;

    @MockBean
    UserRepository userRepository;

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
    void loginTestFailure() {
        LoginRequestDto request = LoginRequestDto.builder()
                        .email("jhonas@udea.edu.co")
                        .password("123")
                        .build();
        GraphQLException exception = assertThrows(GraphQLException.class, () -> authService.login(request));
        String expectedMessage = "Invalid credentials";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void register() {

    }
}