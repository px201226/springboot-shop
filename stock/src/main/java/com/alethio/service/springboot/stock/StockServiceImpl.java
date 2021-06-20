package com.alethio.service.springboot.stock;


import com.alethio.service.common.ItemStatusDTO;
import com.alethio.service.common.ItemType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import com.alethio.service.domain.stock.StockService;
import com.alethio.service.domain.stock.request.IReceivingRequestRepository;
import com.alethio.service.springboot.remote.IItemRemoteService;

@Service
@Transactional
public class StockServiceImpl extends StockService {

    public StockServiceImpl(IItemRemoteService itemService, IReceivingRequestRepository iReceivingRequestRepository) {
        super(itemService, iReceivingRequestRepository);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public ItemStatusDTO addAvailableStock(ItemType itemType, Long itemId, Long quantity) {
        return super.addAvailableStock(itemType, itemId, quantity);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public ItemStatusDTO placeOrder(ItemType itemType, Long itemId, Long quantity) {
        return super.placeOrder(itemType, itemId, quantity);
    }
}
