package co.edu.udea.vuelosback.presenter.controller.auth;

import java.util.Map;

import co.edu.udea.vuelosback.core.dto.UserRegisterRequestDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.udea.vuelosback.core.constantes.AplicationConst;
import co.edu.udea.vuelosback.core.interfaces.IAuthService;
import co.edu.udea.vuelosback.utils.ApplicationUtils;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private IAuthService authService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody(required = true) Map<String, String> requestMap) {
        try {
            return authService.login(requestMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ApplicationUtils.generateResponse(
                Map.of("message", AplicationConst.SOMETHING_WENT_WRONG),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@Valid @RequestBody UserRegisterRequestDto request) {
        try {
            return authService.register(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ApplicationUtils.generateResponse(
                Map.of("message", AplicationConst.SOMETHING_WENT_WRONG),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

}
