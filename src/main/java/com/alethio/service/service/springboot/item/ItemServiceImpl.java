package com.alethio.service.service.springboot.item.service;

import com.alethio.service.service.domain.item.ItemRepositoryProvider;
import com.alethio.service.service.domain.item.ItemService;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl extends ItemService {

    public ItemServiceImpl(ItemRepositoryProvider itemRepositoryProvider) {
        super(itemRepositoryProvider);
    }
}
