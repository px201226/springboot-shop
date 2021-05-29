package com.alethio.service.service.domain.common;

import com.alethio.service.service.domain.item.ItemEntity;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Builder(access = AccessLevel.PRIVATE)
public class ItemStatusDVO {
    private Long id;
    private Long availableStockQuantity;
    private Long requestStockThreshold;
    private Long requestStockQuantity;
    private String itemName;
    private ItemType itemType;
    private String encryptKey;

    public static ItemStatusDVO of(ItemEntity item){
        return ItemStatusDVO.builder()
                .id(item.getId())
                .itemName(item.getName())
                .availableStockQuantity(item.getAvailableStockQuantity())
                .requestStockThreshold(item.getRequestStockThreshold())
                .requestStockQuantity(item.getRequestStockQuantity())
                .itemType(item.getItemType())
                .encryptKey(item.getVendor().encryptKey(item.getName()))
                .build();
    }
}
