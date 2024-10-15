package co.edu.udea.vuelosback.core.interfaces;

import co.edu.udea.vuelosback.core.dto.AuthResponseDto;
import co.edu.udea.vuelosback.core.dto.LoginRequestDto;
import co.edu.udea.vuelosback.core.dto.UserRegisterRequestDto;

public interface IAuthService {

    AuthResponseDto login(LoginRequestDto requestMap);

    AuthResponseDto register(UserRegisterRequestDto request);

}
