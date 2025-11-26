package com.linktic.inventory.dto;

public record ProductResponse(
        ProductData data
) {}

record ProductData(
        Long id,
        String type,
        ProductAttributes attributes
) {}

record ProductAttributes(
        Long id,
        String nombre,
        String descripcion,
        Double precio
) {}
