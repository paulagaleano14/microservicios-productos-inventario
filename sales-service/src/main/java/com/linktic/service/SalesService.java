package com.linktic.sales.service;

import com.linktic.sales.client.InventoryClient;
import com.linktic.sales.dto.CreateSaleRequest;
import com.linktic.sales.dto.ProductResponse;
import com.linktic.sales.model.Sale;
import com.linktic.sales.repository.SalesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SalesService {

    private final SalesRepository repo;
    private final InventoryClient client;

    public Sale createSale(CreateSaleRequest req) {

        ProductResponse product = client.purchase(req.productId(), req.quantity());

        Sale sale = new Sale();
        sale.setProductId(req.productId());
        sale.setQuantity(req.quantity());
        sale.setPrice(product.price());
        sale.setDate(LocalDateTime.now());

        return repo.save(sale);
    }

    public Sale getOne(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Sale not found"));
    }
}
