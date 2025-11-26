package com.linktic.sales.repository;

import com.linktic.sales.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesRepository extends JpaRepository<Sale, Long> {
}
