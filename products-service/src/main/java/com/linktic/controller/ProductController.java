package com.linktic.products.controller;

import com.linktic.products.dto.ProductRequest;
import com.linktic.products.dto.ProductResponse;
import com.linktic.products.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody ProductRequest req) {
        ProductResponse p = service.create(req);

        return ResponseEntity.ok(
                Map.of("data", Map.of(
                        "type", "products",
                        "id", p.getId(),
                        "attributes", p
                ))
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable("id") Long id) {
        ProductResponse p = service.getById(id);

        return ResponseEntity.ok(
                Map.of("data", Map.of(
                        "type", "products",
                        "id", p.getId(),
                        "attributes", p
                ))
        );
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(
                Map.of("data", service.getAll())
        );
    }
}
