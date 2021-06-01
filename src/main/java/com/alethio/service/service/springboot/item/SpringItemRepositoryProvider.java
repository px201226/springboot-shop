package com.alethio.service.service.springboot.item;

import com.alethio.service.service.domain.item.ItemRepositoryProvider;
import com.alethio.service.service.domain.item.IItemRepository;
import com.alethio.service.service.domain.common.ItemType;
import com.alethio.service.service.springboot.item.repository.ClothesRepositoryImpl;
import com.alethio.service.service.springboot.item.repository.FoodRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class SpringItemRepositoryProvider extends ItemRepositoryProvider {

    private static Map<ItemType, IItemRepository> itemRepositoryMap = null;
    private final FoodRepositoryImpl foodRepositoryImpl;
    private final ClothesRepositoryImpl clothesRepositoryImpl;

    @Override
    public IItemRepository getRepositoryByItemType(ItemType itemType) {
        if(itemRepositoryMap == null) {
            initItemRepositoryMap();
        }
        return itemRepositoryMap.get(itemType);
    }

    public int getRegisteredItemRepository(){
        if(itemRepositoryMap == null) {
            initItemRepositoryMap();
        }
        return itemRepositoryMap.size();
    }

    private void initItemRepositoryMap(){
        itemRepositoryMap = new HashMap<>();
        itemRepositoryMap.put(ItemType.CLOTHES , clothesRepositoryImpl);
        itemRepositoryMap.put(ItemType.FOOD    , foodRepositoryImpl);
    }


}
