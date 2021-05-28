package com.alethio.service.service.domain.item.category;

import com.alethio.service.service.domain.item.Item;
import com.alethio.service.service.domain.item.ItemType;

import javax.persistence.*;


@Entity(name = "clothes")
public class Clothes extends Item {

    private static final ItemType ITEM_TYPE = ItemType.CLOTHES;

    @Override
    public ItemType getItemType() {
        return ITEM_TYPE;
    }
}
