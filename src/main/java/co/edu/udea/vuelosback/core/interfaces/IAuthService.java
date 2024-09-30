package co.edu.udea.vuelosback.core.interfaces;

import java.util.Map;

import co.edu.udea.vuelosback.core.dto.AuthResponse;
import co.edu.udea.vuelosback.core.dto.LoginRequestDto;
import co.edu.udea.vuelosback.core.dto.UserRegisterRequestDto;
import org.springframework.http.ResponseEntity;

public interface IAuthService {

    AuthResponse login(LoginRequestDto requestMap);

    AuthResponse register(UserRegisterRequestDto request);

}
