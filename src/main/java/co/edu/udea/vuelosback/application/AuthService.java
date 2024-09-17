package co.edu.udea.vuelosback.application;

import java.util.Map;

import co.edu.udea.vuelosback.core.dao.UserRepository;
import co.edu.udea.vuelosback.core.dto.UserRegisterRequestDto;
import co.edu.udea.vuelosback.core.models.RolesAplicacion;
import co.edu.udea.vuelosback.core.models.User;
import co.edu.udea.vuelosback.utils.ApplicationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import co.edu.udea.vuelosback.security.UserDetailsServ;
import co.edu.udea.vuelosback.security.jwt.JwtUtil;
import co.edu.udea.vuelosback.core.interfaces.IAuthService;

@Service
public class AuthService implements IAuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServ userDetailServ;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<Map<String, String>> login(Map<String, String> requestMap) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(requestMap.get("email"), requestMap.get("password"))
            );

            if (authentication.isAuthenticated()) {
                if (userDetailServ.getUserDetail() != null) {
                    String token = jwtUtil.generateToken(
                            userDetailServ.getUserDetail().getEmail(),
                            userDetailServ.getUserDetail().getRolesAplicacion().getRol()
                    );
                    return ApplicationUtils.generateResponse(
                            Map.of("token", token, "message", "ok"),
                            HttpStatus.OK
                    );
                }
            }

        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al intentar loguearse " + e.getMessage());
        }
        return ApplicationUtils.generateResponse(
                Map.of("message", "Credenciales Incorrectas"),
                HttpStatus.BAD_REQUEST
        );
    }

    @Override
    public ResponseEntity<Map<String, String>> register(UserRegisterRequestDto request) {
        try {

            if(!request.isAcceptTerms()) {
                return ApplicationUtils.generateResponse(
                        Map.of("message", "Debe aceptar los terminos y condiciones"),
                        HttpStatus.BAD_REQUEST
                );
            }

            User exists = userRepository.findByEmail(request.getEmail());
            if (exists != null) {
                return ApplicationUtils.generateResponse(
                        Map.of("message", "Ya existe una cuenta registrada con estre email"),
                        HttpStatus.BAD_REQUEST
                );
            }

            User newUser = User.builder()
                    .fullName(request.getFullName())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .phoneNumber(request.getPhoneNumber())
                    .rolesAplicacion(RolesAplicacion.usuario)
                    .build();
            userRepository.save(newUser);

            return ApplicationUtils.generateResponse(
                    Map.of(
                            "message", "ok",
                            "token", jwtUtil.generateToken(newUser.getEmail(), newUser.getRolesAplicacion().getRol())
                    ),
                    HttpStatus.OK
            );
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al intentar registrar el usuario " + e.getMessage());
            return ApplicationUtils.generateResponse(
                    Map.of("message", "Ha ocurrido un error al intentar registrar el usuario"),
                    HttpStatus.BAD_REQUEST
            );
        }

    }


}
