package com.camiones.service;

import com.camiones.dto.AuthResponse;
import com.camiones.dto.LoginRequest;
import com.camiones.dto.RegisterRequest;
import com.camiones.dto.UsuarioDTO;
import com.camiones.model.Usuario;
import com.camiones.repository.UsuarioRepository;
import com.camiones.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JwtUtil jwtUtil;

    public AuthResponse login(LoginRequest request) {
        Usuario usuario = usuarioRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!usuario.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        String token = jwtUtil.generateToken(usuario.getUsername());
        UsuarioDTO usuarioDTO = new UsuarioDTO(
                usuario.getId(),
                usuario.getUsername(),
                usuario.getEmail(),
                usuario.getEmpresa()
        );

        return new AuthResponse(token, usuarioDTO);
    }

    public AuthResponse register(RegisterRequest request) {
        System.out.println("=== INICIO REGISTRO ===");
        System.out.println("Username: " + request.getUsername());
        System.out.println("Email: " + request.getEmail());
        System.out.println("Empresa: " + request.getEmpresa());
        
        if (usuarioRepository.existsByUsername(request.getUsername())) {
            System.out.println("ERROR: Usuario ya existe");
            throw new RuntimeException("El usuario ya existe");
        }

        if (usuarioRepository.existsByEmail(request.getEmail())) {
            System.out.println("ERROR: Email ya registrado");
            throw new RuntimeException("El email ya está registrado");
        }

        System.out.println("Creando nuevo usuario...");
        Usuario usuario = new Usuario(
                request.getUsername(),
                request.getEmail(),
                request.getPassword(),
                request.getEmpresa()
        );

        System.out.println("Guardando usuario en BD...");
        usuario = usuarioRepository.save(usuario);
        System.out.println("Usuario guardado con ID: " + usuario.getId());

        System.out.println("Generando token JWT...");
        String token = jwtUtil.generateToken(usuario.getUsername());
        System.out.println("Token generado exitosamente");
        
        UsuarioDTO usuarioDTO = new UsuarioDTO(
                usuario.getId(),
                usuario.getUsername(),
                usuario.getEmail(),
                usuario.getEmpresa()
        );

        System.out.println("=== REGISTRO EXITOSO ===");
        return new AuthResponse(token, usuarioDTO);
    }
}
