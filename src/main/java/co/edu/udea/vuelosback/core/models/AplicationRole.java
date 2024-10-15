package co.edu.udea.vuelosback.core.models;

import lombok.Getter;

@Getter
public enum AplicationRole {
    administrador("ADMIN"),
    usuario("USER");

    private final String rol;

    AplicationRole(String rol) {
        this.rol = rol;
    }

}
