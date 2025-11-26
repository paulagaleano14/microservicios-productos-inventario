package com.linktic.products.dto;

import lombok.Data;

@Data
public class ProductResponse {
    private Long id;
    private String nombre;
    private double precio;
    private String descripcion;
}
