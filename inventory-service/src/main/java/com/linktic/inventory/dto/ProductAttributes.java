package com.linktic.inventory.dto;

public record ProductAttributes(
        Long id,
        String nombre,
        String descripcion,
        Double precio
) {}
