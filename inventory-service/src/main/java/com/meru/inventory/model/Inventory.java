package com.meru.inventory.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Inventory {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="INVENTORY_ID")
    private Long id;

    @Column(unique=true)
    private Long productId;

    private Long quantity;

    private String supplier;

}
