package com.meru.inventory.service;

import com.meru.inventory.domain.InventoryInformation;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface InventoryService {

    List<InventoryInformation> findAllInventory();

    ResponseEntity<Object> findInventory(Long inventoryId);

    ResponseEntity<Object> createInventory(InventoryInformation inventoryInformation);

    ResponseEntity<Object> updateInventory(InventoryInformation inventoryInformation, Long inventoryId);

    ResponseEntity<Object> deleteInventory(Long inventoryId);

    ResponseEntity<Object> findInventoryByProductId(Long productId);

    ResponseEntity<Object> updateInventoryByProductId(InventoryInformation inventoryInformation,Long productId);

    ResponseEntity<Object> deleteInventoryByProductId(Long productId);

}
