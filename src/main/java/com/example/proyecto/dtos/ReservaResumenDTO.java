// ReservaResumenDTO.java
package com.example.proyecto.dtos;

public record ReservaResumenDTO(
        long pendientes,
        long aprobadas,
        long rechazadas) {
}
