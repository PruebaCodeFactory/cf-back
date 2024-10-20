package co.edu.udea.vuelosback.presenter.controller.user;

import co.edu.udea.vuelosback.application.UserService;
import co.edu.udea.vuelosback.core.dto.UserResponseDto;
import co.edu.udea.vuelosback.core.models.AplicationRole;
import co.edu.udea.vuelosback.core.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc
class UserControllerTest {

    private UUID id = UUID.randomUUID();
    private UserResponseDto user;
    @Autowired
    private GraphQlTester graphQlTester;

    @MockBean
    private UserService userService;

    @BeforeEach
    void setUp() {
        user = UserResponseDto.builder()
                .id(this.id)
                .email("jhonas@udea.edu.co")
                .role("Usuario")
                .phoneNumber("3111111111")
                .fullName("Jhonatan Granda")
                .build();
    }

    @Test
    void getUserFound() {
        Mockito.when(userService.getUserById(this.id)).thenReturn(user);
        mockMvc.perform(get("/user/{id}", this.id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(this.id.toString()))
                .andExpect(jsonPath("$.email").value("jhonas@udea.edu.co")) // Verifica otros campos como el email
                .andExpect(jsonPath("$.fullName").value("Jhonatan Granda"));
    }
}