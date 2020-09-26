package com.meru.inventory.service.mapper;

import com.meru.inventory.domain.InventoryInformation;
import com.meru.inventory.model.Inventory;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface InventoryMapper {

    List<InventoryInformation> mapToInventories(Iterable<Inventory> inventoryList);

    InventoryInformation inventoryToInventoryInformation(Inventory inventory);

    Inventory inventoryInformationToInventory(InventoryInformation inventoryInformation);
}
