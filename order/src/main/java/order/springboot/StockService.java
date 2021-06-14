package order.springboot;

import order.domain.common.ItemStatusDTO;
import order.domain.common.ItemType;
import org.springframework.stereotype.Service;

@Service
public class StockService implements IStockService {
    @Override
    public ItemStatusDTO placeOrder(ItemType itemType, Long itemId, Long quantity) {
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
