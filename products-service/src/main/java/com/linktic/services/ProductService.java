package com.linktic.products.service;

import com.linktic.products.dto.ProductRequest;
import com.linktic.products.dto.ProductResponse;
import com.linktic.products.exception.ProductNotFoundException;
import com.linktic.products.model.Product;
import com.linktic.products.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;

    public ProductResponse create(ProductRequest req) {
        Product p = new Product();
        p.setNombre(req.getNombre());
        p.setPrecio(req.getPrecio());
        p.setDescripcion(req.getDescripcion());

        repository.save(p);

        return map(p);
    }

    public ProductResponse getById(Long id) {
        Product p = repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        return map(p);
    }

    public List<ProductResponse> getAll() {
        return repository.findAll().stream()
                .map(this::map)
                .toList();
    }

    private ProductResponse map(Product p) {
        ProductResponse r = new ProductResponse();
        r.setId(p.getId());
        r.setNombre(p.getNombre());
        r.setPrecio(p.getPrecio());
        r.setDescripcion(p.getDescripcion());
        return r;
    }
}
