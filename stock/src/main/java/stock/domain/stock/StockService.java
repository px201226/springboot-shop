package stock.domain.stock;



import com.alethio.service.common.ItemStatusDTO;
import com.alethio.service.common.ItemType;
import org.springframework.http.ResponseEntity;
import stock.domain.stock.request.IReceivingRequestRepository;
import stock.domain.stock.request.ReceivingRequestEntity;
import stock.springboot.remote.IItemRemoteService;

import java.util.Optional;

public class StockService implements IStockService {

    private IItemRemoteService itemService;
    private IReceivingRequestRepository iReceivingRequestRepository;

    public StockService(IItemRemoteService itemService, IReceivingRequestRepository iReceivingRequestRepository) {
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
