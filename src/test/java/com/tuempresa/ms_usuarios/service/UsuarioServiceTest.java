package com.tuempresa.ms_usuarios.service;

import com.tuempresa.ms_usuarios.model.Usuario;
import com.tuempresa.ms_usuarios.repository.UsuarioRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository repository;

    @InjectMocks
    private UsuarioService service;

    private Usuario usuario;

    @BeforeEach
    void setup() {

        MockitoAnnotations.openMocks(this);

        usuario = Usuario.builder()
                .id(1L)
                .nombre("Denizard")
                .correo("denizard@gmail.com")
                .password("123456")
                .build();
    }

    @Test
    void deberiaGuardarUsuarioCorrectamente() {

        when(repository.findByCorreo(usuario.getCorreo()))
                .thenReturn(Optional.empty());

        when(repository.save(usuario))
                .thenReturn(usuario);

        Usuario resultado = service.guardar(usuario);

        assertNotNull(resultado);
        assertEquals("Denizard", resultado.getNombre());

        verify(repository, times(1)).save(usuario);
    }

    @Test
    void deberiaLanzarErrorSiCorreoExiste() {

        when(repository.findByCorreo(usuario.getCorreo()))
                .thenReturn(Optional.of(usuario));

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> service.guardar(usuario)
        );

        assertEquals("El correo ya existe", exception.getMessage());

        verify(repository, never()).save(usuario);
    }
}