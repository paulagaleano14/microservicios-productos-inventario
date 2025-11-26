package com.linktic.sales.controller;

import com.linktic.sales.dto.CreateSaleRequest;
import com.linktic.sales.model.Sale;
import com.linktic.sales.service.SalesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sales")
@RequiredArgsConstructor
public class SalesController {

    private final SalesService service;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody CreateSaleRequest req) {
        Sale sale = service.createSale(req);
        return ResponseEntity.ok(sale);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        return ResponseEntity.ok(service.getOne(id));
    }
}
