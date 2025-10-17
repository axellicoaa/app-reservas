package com.example.proyecto.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.proyecto.dtos.UsuarioDTO;
import com.example.proyecto.models.UsuarioModel;
import com.example.proyecto.services.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }


    @PostMapping("/area/{idArea}")
    public UsuarioDTO crearUsuarioConArea(@PathVariable Long idArea, @RequestBody UsuarioModel usuario) {
        return service.crearUsuarioConArea(idArea, usuario);
    }


    @PutMapping("/{idUsuario}/area/{idArea}")
    public UsuarioDTO asignarArea(@PathVariable Long idUsuario, @PathVariable Long idArea) {
        return service.asignarArea(idUsuario, idArea);
    }


    @GetMapping
    public List<UsuarioDTO> listarUsuarios() {
        return service.listarUsuarios();
    }


    @GetMapping("/{idUsuario}")
    public UsuarioDTO obtenerUsuario(@PathVariable Long idUsuario) {
        return service.obtenerUsuario(idUsuario);
    }


    @GetMapping("/area/{idArea}")
    public List<UsuarioDTO> listarPorArea(@PathVariable Long idArea) {
        return service.listarUsuariosPorArea(idArea);
    }
}
