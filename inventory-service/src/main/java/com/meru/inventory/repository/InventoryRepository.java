package com.meru.inventory.repository;

import com.meru.inventory.model.Inventory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface InventoryRepository extends CrudRepository<Inventory, Long> {

    Optional<Inventory> findByProductId(Long productId);
}