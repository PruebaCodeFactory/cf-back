package co.edu.udea.vuelosback.application;

import co.edu.udea.vuelosback.core.dao.UserRepository;
import co.edu.udea.vuelosback.core.dto.AuthResponseDto;
import co.edu.udea.vuelosback.core.dto.LoginRequestDto;
import co.edu.udea.vuelosback.core.dto.UserRegisterRequestDto;
import co.edu.udea.vuelosback.core.models.AplicationRole;
import co.edu.udea.vuelosback.core.models.User;
import graphql.GraphQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import co.edu.udea.vuelosback.security.jwt.JwtUtil;
import co.edu.udea.vuelosback.core.interfaces.IAuthService;

@Service
public class AuthService implements IAuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public AuthResponseDto login(LoginRequestDto request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
            User user = userRepository.findByEmail(request.getEmail());
            return new AuthResponseDto(jwtUtil.generateToken(
                    user.getId(),
                    user.getEmail(),
                    user.getAplicationRole().getRol()
            ));
        } catch (BadCredentialsException e) {
            throw new GraphQLException("Invalid credentials");
        }
    }


    @Override
    public AuthResponseDto register(UserRegisterRequestDto request) {
        try {

            if (!request.isAcceptTerms()) {
                throw new GraphQLException("You must accept terms and conditions");
            }

            User exists = userRepository.findByEmail(request.getEmail());
            if (exists != null) {
                throw new GraphQLException("Already exists an user with this email");
            }

            User newUser = User.builder()
                    .fullName(request.getFullName())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .phoneNumber(request.getPhoneNumber())
                    .aplicationRole(AplicationRole.usuario)
                    .build();
            userRepository.save(newUser);

            return new AuthResponseDto(jwtUtil.generateToken(
                    newUser.getId(),
                    newUser.getEmail(),
                    newUser.getAplicationRole().getRol()
            ));
        } catch (Exception e) {
            throw new GraphQLException(e.getMessage());
        }
    }

}
