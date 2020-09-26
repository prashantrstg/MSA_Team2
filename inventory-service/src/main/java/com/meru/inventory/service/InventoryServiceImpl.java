package com.meru.inventory.service;

import com.meru.inventory.domain.InventoryInformation;
import com.meru.inventory.model.Inventory;
import com.meru.inventory.repository.InventoryRepository;
import com.meru.inventory.service.mapper.InventoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class InventoryServiceImpl implements InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private InventoryMapper inventoryMapper;

    @Override
    public List<InventoryInformation> findAllInventory() {
        Iterable<Inventory> inventories = inventoryRepository.findAll();
        return inventoryMapper.mapToInventories(inventories);
    }

    @Override
    public ResponseEntity<Object> findInventory(Long inventoryId) {
        Optional<Inventory> inventory = inventoryRepository.findById(inventoryId);
        if (inventory.isPresent()) {
            InventoryInformation inventoryInformation = inventoryMapper.inventoryToInventoryInformation(inventory.get());
            return ResponseEntity.status(HttpStatus.OK).body(inventoryInformation);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Inventory does not exist.");
        }
    }

    @Override
    public ResponseEntity<Object> createInventory(InventoryInformation inventoryInformation) {
        String result = "Default Error";
        Inventory inventory = inventoryMapper.inventoryInformationToInventory(inventoryInformation);
        Inventory sInventory = inventoryRepository.save(inventory);
        return ResponseEntity.status(HttpStatus.CREATED).body("Success: Inventory created.");
    }

    @Override
    public ResponseEntity<Object> updateInventory(InventoryInformation inventoryInformation, Long inventoryId) {
        Optional<Inventory> inventory = inventoryRepository.findById(inventoryId);
        if (inventory.isPresent()) {
            Inventory updatedInventory = inventoryMapper.inventoryInformationToInventory(inventoryInformation);
            updatedInventory.setId(inventoryId);
            inventoryRepository.save(updatedInventory);
            return ResponseEntity.status(HttpStatus.OK).body("Success: Inventory updated.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Inventory does not exist.");
        }
    }

    @Override
    public ResponseEntity<Object> deleteInventory(Long inventoryId) {
        Optional<Inventory> inventory = inventoryRepository.findById(inventoryId);
        if (inventory.isPresent()) {
            inventoryRepository.deleteById(inventoryId);
            return ResponseEntity.status(HttpStatus.OK).body("Success: Inventory deleted.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Inventory does not exist.");
        }
    }

    @Override
    public ResponseEntity<Object> findInventoryByProductId(Long productId) {
        Optional<Inventory> inventory = inventoryRepository.findByProductId(productId);
        if(inventory.isPresent()){
            Inventory tInventory = inventory.get();
            return ResponseEntity.status(HttpStatus.OK).body(inventoryMapper.inventoryToInventoryInformation(tInventory));
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Inventory does not exist with this product id.");
        }

    }

    @Override
    public ResponseEntity<Object> updateInventoryByProductId(InventoryInformation inventoryInformation,Long productId) {
        Optional<Inventory> inventory = inventoryRepository.findByProductId(productId);
        if(inventory.isPresent()){
            Inventory updatedInventory = inventoryMapper.inventoryInformationToInventory(inventoryInformation);
            updatedInventory.setId(inventory.get().getId());
            updatedInventory.setProductId(productId);
            inventoryRepository.save(updatedInventory);
            return ResponseEntity.status(HttpStatus.OK).body("Success: Inventory updated with product id.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Inventory does not exist with this product id.");
        }
    }

    @Override
    public ResponseEntity<Object> deleteInventoryByProductId(Long productId) {
        Optional<Inventory> inventory = inventoryRepository.findByProductId(productId);
        if (inventory.isPresent()) {
            inventoryRepository.deleteById(inventory.get().getId());
            return ResponseEntity.status(HttpStatus.OK).body("Success: Inventory deleted with product id.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Inventory does not exist with product id.");
        }
    }
}
