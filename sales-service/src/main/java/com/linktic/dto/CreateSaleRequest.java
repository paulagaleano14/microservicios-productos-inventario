package com.linktic.sales.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CreateSaleRequest(
        @NotNull Long productId,
        @Min(1) Integer quantity
) {}
