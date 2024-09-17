package co.edu.udea.vuelosback.core.interfaces;

import java.util.Map;

import co.edu.udea.vuelosback.core.dto.UserRegisterRequestDto;
import org.springframework.http.ResponseEntity;

public interface IAuthService {

    ResponseEntity<Map<String, String>> login(Map<String, String> requestMap);

    ResponseEntity<Map<String, String>> register(UserRegisterRequestDto request);

}
