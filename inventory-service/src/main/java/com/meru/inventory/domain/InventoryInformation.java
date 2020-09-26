package com.meru.inventory.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InventoryInformation {
    private Long id;

    private Long productId;

    private Long quantity;

    private String supplier;
}
