package co.edu.udea.vuelosback.presenter.controller.auth;

import co.edu.udea.vuelosback.core.dto.AuthResponseDto;
import co.edu.udea.vuelosback.core.dto.LoginRequestDto;
import co.edu.udea.vuelosback.core.dto.UserRegisterRequestDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import co.edu.udea.vuelosback.core.interfaces.IAuthService;

@Controller
public class AuthController {

    @Autowired
    private IAuthService authService;

    @MutationMapping
    public AuthResponseDto login(@Argument LoginRequestDto request) {
        return authService.login(request);
    }

    @MutationMapping
    public AuthResponseDto register(@Valid @Argument UserRegisterRequestDto request) {
        return authService.register(request);
    }

}
