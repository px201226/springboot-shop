package com.alethio.service.service.springboot.service;

import com.alethio.service.service.domain.item.AbstractItemRepositoryProvider;
import com.alethio.service.service.domain.item.ItemService;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl extends ItemService {

    public ItemServiceImpl(AbstractItemRepositoryProvider itemRepositoryProvider) {
        super(itemRepositoryProvider);
    }
}
