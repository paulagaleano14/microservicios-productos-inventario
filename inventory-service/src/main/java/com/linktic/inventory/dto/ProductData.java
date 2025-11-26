package com.linktic.inventory.dto;

public record ProductData(
        Long id,
        String type,
        ProductAttributes attributes
) {}
