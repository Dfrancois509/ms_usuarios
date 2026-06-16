package com.tuempresa.ms_usuarios.controller;
import com.tuempresa.ms_usuarios.dto.UsuarioRequestDTO;
import com.tuempresa.ms_usuarios.dto.UsuarioResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import com.tuempresa.ms_usuarios.model.Usuario;
import com.tuempresa.ms_usuarios.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import io.swagger.v3.oas.annotations.tags.Tag;
@RestController
@RequestMapping("/api/v1/usuarios")
@RequiredArgsConstructor
@Tag(
        name = "Usuarios",
        description = "Operaciones relacionadas con usuarios"
)
public class UsuarioController {

    private final UsuarioService service;

    @Operation(
            summary = "Listar usuarios",
            description = "Obtiene todos los usuarios registrados"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuarios obtenidos correctamente")
    })
    @GetMapping
    public ResponseEntity<List<Usuario>> listar() {

        return ResponseEntity.ok(service.listar());
    }

    @PostMapping
    @Operation(
            summary = "Crear usuario",
            description = "Registra un nuevo usuario en el sistema"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario creado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public ResponseEntity<UsuarioResponseDTO> crear(
            @Valid @RequestBody UsuarioRequestDTO request
    ) {

        Usuario usuario = Usuario.builder()
                .nombre(request.getNombre())
                .correo(request.getCorreo())
                .password(request.getPassword())
                .build();

        Usuario usuarioGuardado = service.guardar(usuario);

        UsuarioResponseDTO response = UsuarioResponseDTO.builder()
                .id(usuarioGuardado.getId())
                .nombre(usuarioGuardado.getNombre())
                .correo(usuarioGuardado.getCorreo())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {

        service.eliminar(id);

        return ResponseEntity.ok("Usuario eliminado");
    }

}