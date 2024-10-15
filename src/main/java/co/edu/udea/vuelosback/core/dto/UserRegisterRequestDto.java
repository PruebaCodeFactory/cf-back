package co.edu.udea.vuelosback.core.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterRequestDto {
    @NotBlank(message = "El nombre completo es obligatorio")
    private String fullName;

    @Email(message = "El correo electrónico no es válido")
    @NotBlank(message = "El correo electrónico es obligatorio")
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    private String password;

    @NotBlank(message = "El número de teléfono es obligatorio")
    private String phoneNumber;

    @NotNull(message = "Debe aceptar los términos y condiciones")
    private boolean acceptTerms;
}
