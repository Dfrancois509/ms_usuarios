package com.tuempresa.ms_usuarios.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Datos necesarios para crear un usuario")
public class UsuarioRequestDTO {

    @Schema(
            description = "Nombre completo del usuario",
            example = "Denizard François"
    )
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @Schema(
            description = "Correo electrónico del usuario",
            example = "denizard@gmail.com"
    )
    @Email(message = "Correo inválido")
    @NotBlank(message = "El correo es obligatorio")
    private String correo;

    @Schema(
            description = "Contraseña del usuario",
            example = "123456",
            accessMode = Schema.AccessMode.WRITE_ONLY
    )
    @NotBlank(message = "La contraseña es obligatoria")
    private String password;
}