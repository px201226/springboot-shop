package com.alethio.service.service.springboot.common;

import com.alethio.service.service.domain.item.AbstractItemRepositoryProvider;
import com.alethio.service.service.domain.item.IItemRepository;
import com.alethio.service.service.domain.item.ItemType;
import com.alethio.service.service.springboot.repository.ClothesRepository;
import com.alethio.service.service.springboot.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@RequiredArgsConstructor
@Component
public class BeanItemRepositoryProvider extends AbstractItemRepositoryProvider {

    private final ApplicationContextProvider applicationContextProvider;
    private final ClothesRepository clothesRepository;
    private final FoodRepository foodRepository;
    private static Map<ItemType, IItemRepository> itemRepositoryMap = null;


    private void initItemRepositoryMap(){
        itemRepositoryMap = new HashMap<>();
        itemRepositoryMap.put(ItemType.CLOTHES , clothesRepository);
        itemRepositoryMap.put(ItemType.FOOD    , foodRepository);
    }

    @Override
    public IItemRepository getRepositoryByItemType(ItemType itemType) {

        if(itemRepositoryMap == null)
            initItemRepositoryMap();

        return itemRepositoryMap.get(itemType);
    }
}
