package com.tuempresa.ms_usuarios;

import com.tuempresa.ms_usuarios.model.Usuario;
import com.tuempresa.ms_usuarios.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

import net.datafaker.Faker;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

@Profile("dev")
@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;

    @Override
    public void run(String... args) {

        try {

            // Evitar duplicar usuarios
            if (usuarioRepository.count() > 0) {
                System.out.println("La base de datos ya contiene usuarios");
                return;
            }

            Faker faker = new Faker(new Locale("es"));

            Set<String> correos = new HashSet<>();

            for (int i = 0; i < 20; i++) {

                String correo;

                // Garantizar correos únicos
                do {
                    correo = faker.internet().emailAddress();
                } while (correos.contains(correo));

                correos.add(correo);

                Usuario usuario = Usuario.builder()
                        .nombre(faker.name().fullName())
                        .correo(correo)
                        .password("123456")
                        .rol("USER")
                        .build();


                usuarioRepository.save(usuario);
            }

            System.out.println("Usuarios fake cargados correctamente");

        } catch (Exception e) {

            System.out.println("Error al cargar datos fake:");
            e.printStackTrace();
        }
    }
}