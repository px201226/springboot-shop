package com.alethio.service.service.domain.stock;

import com.alethio.service.service.domain.exception.AlreadyRequestedReceivingItemException;
import com.alethio.service.service.domain.item.IItemService;
import com.alethio.service.service.domain.common.ItemStatusDVO;
import com.alethio.service.service.domain.common.ItemType;
import com.alethio.service.service.domain.stock.request.IReceivingRequestRepository;
import com.alethio.service.service.domain.stock.request.ReceivingRequest;

import java.util.Optional;

public class StockService implements IStockService {

    private IItemService itemService;
    private IReceivingRequestRepository iReceivingRequestRepository;

    public StockService(IItemService itemService, IReceivingRequestRepository iReceivingRequestRepository) {
        this.itemService = itemService;
        this.iReceivingRequestRepository = iReceivingRequestRepository;
    }

    @Override
    public Long increaseStockQuantity(ItemType itemType, Long itemId, int quantity) {
        Long afterQuantity = itemService.increaseAvailableStock(itemType,itemId, quantity);

        return afterQuantity;
    }

    @Override
    public Long decreaseStockQuantity(ItemType itemType, Long itemId, int quantity) {
        Long afterQuantity = itemService.decreaseAvailableStock(itemType,itemId, quantity);

        ItemStatusDVO itemStatusDVO = getItemStatus(itemType, itemId);
        if(isStockQuantityLessThreshold(itemStatusDVO)){
            requestReceving(itemStatusDVO);
        }

        return afterQuantity;
    }

    private ReceivingRequest requestReceving(ItemStatusDVO itemStatusDVO){
        Optional<ReceivingRequest> findEntity = iReceivingRequestRepository.findByItemTypeAndItemId(itemStatusDVO.getItemType(), itemStatusDVO.getId());

        if(findEntity.isPresent()){
            return null;
        }

        ReceivingRequest receivingRequest = ReceivingRequest.builder()
                .itemType(itemStatusDVO.getItemType())
                .itemId(itemStatusDVO.getId())
                .itemName(itemStatusDVO.getItemName())
                .encryptKey(itemStatusDVO.getEncryptKey())
                .requestStockQuantity(itemStatusDVO.getRequestStockQuantity())
                .build();

        return iReceivingRequestRepository.save(receivingRequest);
    }

    private ItemStatusDVO getItemStatus(ItemType itemType, Long itemId){
        ItemStatusDVO itemStatus = itemService.getItemStatus(itemType, itemId);
        return itemStatus;
    }

    private boolean isStockQuantityLessThreshold(ItemStatusDVO itemStatusDVO){
        return itemStatusDVO.getAvailableStockQuantity() < itemStatusDVO.getRequestStockThreshold();
    }
}
