package com.woopaca.itemservice.domain.item;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Item {

    private Long id;
    private String itemName;
    private Integer price;
    private Integer quantity;

    @Builder
    public Item(Long id, String itemName, Integer price, Integer quantity) {
        this.id = id;
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
