package com.tuempresa.ms_usuarios.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "Datos de respuesta del usuario")
public class UsuarioResponseDTO {

    @Schema(
            description = "ID único del usuario",
            example = "1"
    )
    private Long id;

    @Schema(
            description = "Nombre del usuario",
            example = "Denizard François"
    )
    private String nombre;

    @Schema(
            description = "Correo electrónico",
            example = "denizard@gmail.com"
    )
    private String correo;
}