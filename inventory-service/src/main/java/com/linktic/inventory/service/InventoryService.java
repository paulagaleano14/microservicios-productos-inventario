package com.linktic.inventory.service;

import com.linktic.inventory.client.ProductClient;
import com.linktic.inventory.dto.ProductResponse;
import com.linktic.inventory.dto.PurchaseRequest;
import com.linktic.inventory.model.Inventory;
import com.linktic.inventory.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;


@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository repo;
    private final ProductClient productClient;

    public Inventory create(Long productId, Integer quantity) {
        Inventory inv = new Inventory(productId, quantity);
        return repo.save(inv);
    }

    public Inventory getOne(Long id) {
        System.out.println(">>> buscando inventario: " + id);

        return repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "No hay inventario para este producto"
                ));
    }

    public Inventory updateStock(Long id, Integer quantity) {
        Inventory inv = getOne(id);
        inv.setQuantity(quantity);
        return repo.save(inv);
    }

    public ProductResponse purchase(PurchaseRequest req) {

        Inventory inv = getOne(req.productId());

        if (inv.getQuantity() < req.quantity()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "No hay stock suficiente"
            );
        }

        inv.setQuantity(inv.getQuantity() - req.quantity());
        repo.save(inv);

        // Obtener el producto del microservicio products-service
        return productClient.getOne(req.productId());
    }
}
