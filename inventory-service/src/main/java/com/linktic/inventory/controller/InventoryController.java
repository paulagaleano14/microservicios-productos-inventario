package com.linktic.inventory.controller;

import com.linktic.inventory.dto.PurchaseRequest;
import com.linktic.inventory.model.Inventory;
import com.linktic.inventory.service.InventoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService service;

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getOne(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id,
                                    @RequestParam("quantity") Integer quantity) {

        Inventory inv = service.updateStock(id, quantity);

        return ResponseEntity.ok(
                Map.of("data", Map.of(
                        "type", "inventory",
                        "id", id,
                        "attributes", inv
                ))
        );
    }

    @PostMapping
    public ResponseEntity<?> create(
            @RequestParam("productId") Long productId,
            @RequestParam("quantity") Integer quantity) {

        Inventory inv = service.create(productId, quantity);

        return ResponseEntity.ok(
                Map.of("data", Map.of(
                        "type", "inventory",
                        "id", inv.getProductId(),
                        "attributes", inv
                ))
        );
    }

    @PostMapping("/purchase")
    public ResponseEntity<?> purchase(@Valid @RequestBody PurchaseRequest req) {

        var product = service.purchase(req);

        return ResponseEntity.ok(
                Map.of("data", Map.of(
                        "type", "purchase",
                        "id", req.productId(),
                        "attributes", Map.of(
                                "product", product,
                                "quantity", req.quantity()
                        )
                ))
        );
    }
}
