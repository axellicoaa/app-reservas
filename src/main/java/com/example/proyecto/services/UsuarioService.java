package com.example.proyecto.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.proyecto.models.UsuarioModel;
import com.example.proyecto.repositories.UsuarioRepository;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<UsuarioModel> getUsuariosByAreaId(Long areaId) {
        return usuarioRepository.findByAreaId(areaId);
    }

    public List<UsuarioModel> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<UsuarioModel> getUsuarioById(Long id) {
        return usuarioRepository.findById(id);
    }

    public UsuarioModel saveUsuario(UsuarioModel usuario) {
        validarUsuario(usuario);
        return usuarioRepository.save(usuario);
    }

    public UsuarioModel updateUsuario(Long id, UsuarioModel usuarioDetails) {
        validarUsuario(usuarioDetails);
        Optional<UsuarioModel> optionalUsuario = usuarioRepository.findById(id);
        if (optionalUsuario.isPresent()) {
            UsuarioModel usuario = optionalUsuario.get();
            usuario.setNombre(usuarioDetails.getNombre());
            usuario.setEmail(usuarioDetails.getEmail());
            usuario.setRuc_ci(usuarioDetails.getRuc_ci());
            usuario.setRol(usuarioDetails.getRol());
            usuario.setPassword(usuarioDetails.getPassword());
            usuario.setArea(usuarioDetails.getArea());
            return usuarioRepository.save(usuario);
        } else {
            return null;
        }
    }

    public void deleteUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    private void validarUsuario(UsuarioModel usuario) {
        if (!esTextoValido(usuario.getNombre())) {
            throw new IllegalArgumentException("El nombre solo debe contener letras y espacios.");
        }
        if (!esTextoValido(usuario.getRol())) {
            throw new IllegalArgumentException("El rol solo debe contener letras y espacios.");
        }
        if (!esTextoValido(usuario.getRuc_ci())) {
            throw new IllegalArgumentException("El RUC/CI solo debe contener letras y espacios.");
        }
        if (usuario.getEmail() == null || usuario.getEmail().isEmpty() || !usuario.getEmail().contains("@")) {
            throw new IllegalArgumentException("El email debe ser válido y contener '@'.");
        }
        if (usuario.getPassword() == null || usuario.getPassword().length() < 6) {
            throw new IllegalArgumentException("La contraseña debe tener al menos 6 caracteres.");
        }
        if (usuario.getArea() == null) {
            throw new IllegalArgumentException("El área no puede ser nula.");
        }

    }

    private boolean esTextoValido(String texto) {
        if (texto == null || texto.isEmpty()) {
            return false;
        }
        for (char c : texto.toCharArray()) {
            if (!Character.isLetter(c) && !Character.isSpaceChar(c)) {
                return false;
            }
        }
        return true;
    }
}
