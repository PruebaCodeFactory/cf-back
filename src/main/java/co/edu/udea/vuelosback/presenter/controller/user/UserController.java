package co.edu.udea.vuelosback.presenter.controller.user;

import co.edu.udea.vuelosback.core.dto.UserResponseDto;
import co.edu.udea.vuelosback.core.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
public class UserController {

    @Autowired
    private IUserService userService;

    @PreAuthorize("isAuthenticated()")
    @QueryMapping
    public UserResponseDto getUser(@Argument UUID id) {
        return userService.getUserById(id);
    }


}
