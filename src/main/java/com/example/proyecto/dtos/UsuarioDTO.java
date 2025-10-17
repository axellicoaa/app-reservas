package com.example.proyecto.dtos;

import com.example.proyecto.models.UsuarioModel;

public record UsuarioDTO(
        Long id,
        String nombre,
        String email,
        String rol,
        String areaNombre
) {
    public static UsuarioDTO fromEntity(UsuarioModel usuario) {
        return new UsuarioDTO(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getRol(),
                usuario.getArea() != null ? usuario.getArea().getNombreArea() : null
        );
    }
}
