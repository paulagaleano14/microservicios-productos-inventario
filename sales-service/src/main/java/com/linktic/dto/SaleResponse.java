package com.linktic.sales.dto;

public record SaleResponse(
        Long id,
        Long productId,
        Integer quantity,
        Double price
) {}
