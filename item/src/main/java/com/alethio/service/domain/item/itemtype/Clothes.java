package com.alethio.service.domain.item.itemtype;


import com.alethio.service.common.ItemType;
import com.alethio.service.domain.item.ItemEntity;
import com.alethio.service.domain.item.Vendor;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Entity(name = "clothes")
public class Clothes extends ItemEntity {

    private static final ItemType ITEM_TYPE = ItemType.CLOTHES;

    @Builder
    public Clothes(Vendor vendor, Long availableStockQuantity, Long requestStockThreshold, Long requestStockQuantity, String name) {
        super(vendor, availableStockQuantity, requestStockThreshold, requestStockQuantity, name);
    }

    @Override
    public ItemType getItemType() {
        return ITEM_TYPE;
    }


}
