package com.alethio.service.service.springboot.stock.service;


import com.alethio.service.service.domain.item.IItemService;
import com.alethio.service.service.domain.stock.StockService;
import com.alethio.service.service.domain.stock.request.IReceivingRequestRepository;
import org.springframework.stereotype.Service;

@Service
public class StockServiceImpl extends StockService {

    public StockServiceImpl(IItemService itemService, IReceivingRequestRepository iReceivingRequestRepository) {
        super(itemService, iReceivingRequestRepository);
    }
}
