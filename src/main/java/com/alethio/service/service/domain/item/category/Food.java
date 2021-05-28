package com.alethio.service.service.domain.item.category;


import com.alethio.service.service.domain.item.Item;
import com.alethio.service.service.domain.item.ItemType;
import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "food")
public class Food extends Item {

    private static final ItemType ITEM_TYPE = ItemType.FOOD;

    @Override
    public ItemType getItemType() {
        return ITEM_TYPE;
    }

    @Builder
    public Food(Long stockQuantity, Long stockRequestThreshold, String productName) {
        super(stockQuantity, stockRequestThreshold, productName);
    }
}
