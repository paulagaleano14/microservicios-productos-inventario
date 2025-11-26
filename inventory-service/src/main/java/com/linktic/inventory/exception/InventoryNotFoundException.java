package com.linktic.inventory.exception;

public class InventoryNotFoundException extends RuntimeException {
    public InventoryNotFoundException(Long productId) {
        super("No se encontro el producto: " + productId + " en el inventario");
    }
}
