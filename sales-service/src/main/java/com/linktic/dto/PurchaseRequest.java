package com.linktic.sales.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record PurchaseRequest(
        @NotNull Long productId,
        @Min(1) Integer quantity
) {}
