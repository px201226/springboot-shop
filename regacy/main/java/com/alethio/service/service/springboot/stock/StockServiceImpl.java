package com.alethio.service.service.springboot.stock;


import com.alethio.service.service.domain.common.ItemStatusDTO;
import com.alethio.service.service.domain.common.ItemType;
import com.alethio.service.service.domain.item.IItemService;
import com.alethio.service.service.domain.stock.StockService;
import com.alethio.service.service.domain.stock.request.IReceivingRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Service
@Transactional
public class StockServiceImpl extends StockService {

    @PersistenceContext
    private EntityManager entityManager;

    public StockServiceImpl(IItemService itemService, IReceivingRequestRepository iReceivingRequestRepository) {
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
        System.out.println(entityManager);
        return super.placeOrder(itemType, itemId, quantity);
    }
}
