package stock.springboot;

import org.springframework.stereotype.Service;
import stock.domain.common.ItemStatusDTO;
import stock.domain.common.ItemType;
import stock.domain.exception.business.OutOfStockQuantityException;

@Service
public class ItemService implements IItemService{
    @Override
    public ItemStatusDTO removeAvailableStock(ItemType itemType, Long itemId, Long quantity) throws OutOfStockQuantityException {
        return null;
    }

    @Override
    public ItemStatusDTO getItemStatus(ItemType itemType, Long itemId) {
        return null;
    }

    @Override
    public ItemStatusDTO addAvailableStock(ItemType itemType, Long itemId, Long quantity) {
        return null;
    }
}
