package com.meru.inventory.controller;

import com.meru.inventory.domain.InventoryInformation;
import com.meru.inventory.service.InventoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("inventory")
@Api(tags = { "Inventory REST endpoints" })
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @PostMapping(path = "/create")
    @ApiOperation(value = "Crete a new inventory", notes = "Create an new inventory with product details.")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error") })

    public ResponseEntity<Object> createInventory(@RequestBody InventoryInformation inventoryInformation) {

        return inventoryService.createInventory(inventoryInformation);
    }

    @GetMapping(path = "/find")
    @ApiOperation(value = "Get all inventory details", notes = "Find all inventory details")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error") })

    public List<InventoryInformation> getAll() {

        return inventoryService.findAllInventory();
    }

    @GetMapping(path = "/find/{inventoryId}")
    @ApiOperation(value = "Get inventory details", notes = "Find inventory details by inventory id")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error") })

    public ResponseEntity<Object> getByInventoryId(@PathVariable Long inventoryId) {

        return inventoryService.findInventory(inventoryId);
    }

    @PutMapping(path = "/update/{inventoryId}")
    @ApiOperation(value = "Update inventory", notes = "Update inventory by inventory id.")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Object.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error") })
    public ResponseEntity<Object> updateInventory(@RequestBody InventoryInformation inventoryInformation,
                                                             @PathVariable Long inventoryId) {

        return inventoryService.updateInventory(inventoryInformation, inventoryId);
    }

    @DeleteMapping(path = "/delete/{inventoryId}")
    @ApiOperation(value = "Delete inventory", notes = "Delete inventory by inventory id.")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Object.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error") })

    public ResponseEntity<Object> deleteInventory(@PathVariable Long inventoryId) {

        return inventoryService.deleteInventory(inventoryId);
    }

    @GetMapping(path = "/findByProductId/{productId}")
    @ApiOperation(value = "Get inventory details", notes = "Find inventory details by product id")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error") })

    public ResponseEntity<Object> getInventoryByProductId(@PathVariable Long productId) {

        return inventoryService.findInventoryByProductId(productId);
    }

    @PutMapping(path = "/updateByProductId/{productId}")
    @ApiOperation(value = "Update inventory", notes = "Update inventory by product id.")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Object.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error") })
    public ResponseEntity<Object> updateInventoryByProductId(@RequestBody InventoryInformation inventoryInformation,
                                                  @PathVariable Long productId) {

        return inventoryService.updateInventoryByProductId(inventoryInformation, productId);
    }

    @DeleteMapping(path = "/deleteByProductId/{productId}")
    @ApiOperation(value = "Delete inventory", notes = "Delete inventory by product id.")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Object.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error") })

    public ResponseEntity<Object> deleteInventoryByProductId(@PathVariable Long productId) {

        return inventoryService.deleteInventoryByProductId(productId);
    }
}