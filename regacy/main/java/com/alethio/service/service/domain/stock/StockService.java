package com.alethio.service.service.domain.stock;

import com.alethio.service.service.domain.common.ItemStatusDTO;
import com.alethio.service.service.domain.common.ItemType;
import com.alethio.service.service.domain.item.IItemService;
import com.alethio.service.service.domain.stock.request.IReceivingRequestRepository;
import com.alethio.service.service.domain.stock.request.ReceivingRequestEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

public class StockService implements IStockService {

    private IItemService itemService;
    private IReceivingRequestRepository iReceivingRequestRepository;

    public StockService(IItemService itemService, IReceivingRequestRepository iReceivingRequestRepository) {
        this.itemService = itemService;
        this.iReceivingRequestRepository = iReceivingRequestRepository;
    }

    @Override
    public ItemStatusDTO placeOrder(ItemType itemType, Long itemId, Long quantity) {

        ItemStatusDTO itemStatusDTO = itemService.removeAvailableStock(itemType, itemId, quantity);

        if(itemStatusDTO.getIsExceedStockThreshold())
            requestReceving(itemStatusDTO);

        return itemStatusDTO;
    }

    @Override
    public ItemStatusDTO getItemStatus(ItemType itemType, Long itemId) {
        return itemService.getItemStatus(itemType,itemId);
    }

    @Override
    public ItemStatusDTO addAvailableStock(ItemType itemType, Long itemId, Long quantity) {
        ItemStatusDTO itemStatusDTO = itemService.addAvailableStock(itemType, itemId, quantity);
        return itemStatusDTO;
    }


    protected ReceivingRequestEntity requestReceving(ItemStatusDTO itemStatusDTO){

        Optional<ReceivingRequestEntity> findEntity = iReceivingRequestRepository.findByItemTypeAndItemId(itemStatusDTO.getItemType(), itemStatusDTO.getId());

        if(findEntity.isPresent()){
            return null;
        }

        ReceivingRequestEntity receivingRequestEntity = buildReceivingRequestEntity(itemStatusDTO);

        return iReceivingRequestRepository.save(receivingRequestEntity);
    }

    private ReceivingRequestEntity buildReceivingRequestEntity(ItemStatusDTO itemStatusDTO){
        return ReceivingRequestEntity.builder()
                .itemType(itemStatusDTO.getItemType())
                .itemId(itemStatusDTO.getId())
                .itemName(itemStatusDTO.getItemName())
                .encryptKey(itemStatusDTO.getEncryptKey())
                .requestStockQuantity(itemStatusDTO.getRequestStockQuantity())
                .build();
    }

}
