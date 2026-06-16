package com.tuempresa.ms_usuarios.service;

import com.tuempresa.ms_usuarios.model.Usuario;
import com.tuempresa.ms_usuarios.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import com.tuempresa.ms_usuarios.exception.ResourceAlreadyExistsException;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;

    public List<Usuario> listar() {

        return repository.findAll();
    }

    public Usuario guardar(Usuario usuario) {

        repository.findByCorreo(usuario.getCorreo())
                .ifPresent(u -> {

                    throw new ResourceAlreadyExistsException("El correo ya existe");                });

        return repository.save(usuario);
    }

    public void eliminar(Long id) {

        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        repository.delete(usuario);
    }
}