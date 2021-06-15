package stock.domain.stock;


import stock.domain.common.ItemStatusDTO;
import stock.domain.common.ItemType;
import stock.domain.stock.request.IReceivingRequestRepository;
import stock.domain.stock.request.ReceivingRequestEntity;
import stock.springboot.remote.IItemRemoteService;

import java.util.Optional;

public class

StockService implements IStockService {

    private IItemRemoteService itemRemoteService;
    private IReceivingRequestRepository iReceivingRequestRepository;

    public StockService(IItemRemoteService itemService, IReceivingRequestRepository iReceivingRequestRepository) {
        this.itemRemoteService = itemService;
        this.iReceivingRequestRepository = iReceivingRequestRepository;
    }

    @Override
    public ItemStatusDTO placeOrder(ItemType itemType, Long itemId, Long quantity) {

        ItemStatusDTO itemStatusDTO = itemRemoteService.removeAvailableStock(itemType, itemId, quantity);

        if(itemStatusDTO.getIsExceedStockThreshold())
            requestReceving(itemStatusDTO);

        return itemStatusDTO;
    }

    @Override
    public ItemStatusDTO getItemStatus(ItemType itemType, Long itemId) {
        return itemRemoteService.getItemStatus(itemType,itemId);
    }

    @Override
    public ItemStatusDTO addAvailableStock(ItemType itemType, Long itemId, Long quantity) {
        ItemStatusDTO itemStatusDTO = itemRemoteService.addAvailableStock(itemType, itemId, quantity);
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
