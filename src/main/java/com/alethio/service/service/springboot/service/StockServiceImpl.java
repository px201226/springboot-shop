package com.alethio.service.service.springboot.service;


import com.alethio.service.service.domain.item.IItemService;
import com.alethio.service.service.domain.stock.StockService;
import org.springframework.stereotype.Service;

@Service
public class StockServiceImpl extends StockService {

    public StockServiceImpl(IItemService itemService) {
        super(itemService);
    }
}
