package com.saleservice.domain.repositories;

import com.saleservice.domain.entities.SaleItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleItemRepository extends JpaRepository<SaleItem,Long> {
}
