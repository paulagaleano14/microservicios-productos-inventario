package com.linktic.products.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductRequest {
    @NotBlank
    private String nombre;

    @NotNull
    private Double precio;

    private String descripcion;
}
