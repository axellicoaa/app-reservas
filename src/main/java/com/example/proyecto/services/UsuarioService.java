package com.example.proyecto.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.proyecto.dtos.UsuarioDTO;
import com.example.proyecto.models.AreaModel;
import com.example.proyecto.models.UsuarioModel;
import com.example.proyecto.repositories.AreaRepository;
import com.example.proyecto.repositories.UsuarioRepository;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepo;
    private final AreaRepository areaRepo;

    public UsuarioService(UsuarioRepository usuarioRepo, AreaRepository areaRepo) {
        this.usuarioRepo = usuarioRepo;
        this.areaRepo = areaRepo;
    }

    public UsuarioDTO crearUsuarioConArea(Long idArea, UsuarioModel usuario) {
        AreaModel area = areaRepo.findById(idArea)
                .orElseThrow(() -> new RuntimeException("Área no encontrada"));
        usuario.setArea(area);
        UsuarioModel saved = usuarioRepo.save(usuario);
        return UsuarioDTO.fromEntity(saved);
    }

    public UsuarioDTO asignarArea(Long idUsuario, Long idArea) {
        UsuarioModel usuario = usuarioRepo.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        AreaModel area = areaRepo.findById(idArea)
                .orElseThrow(() -> new RuntimeException("Área no encontrada"));
        usuario.setArea(area);
        UsuarioModel updated = usuarioRepo.save(usuario);
        return UsuarioDTO.fromEntity(updated);
    }

    public List<UsuarioDTO> listarUsuarios() {
        return usuarioRepo.findAll()
                .stream()
                .map(UsuarioDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public UsuarioDTO obtenerUsuario(Long idUsuario) {
        return usuarioRepo.findById(idUsuario)
                .map(UsuarioDTO::fromEntity)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    public List<UsuarioDTO> listarUsuariosPorArea(Long idArea) {
        return usuarioRepo.findByAreaId(idArea)
                .stream()
                .map(UsuarioDTO::fromEntity)
                .collect(Collectors.toList());
    }
}
