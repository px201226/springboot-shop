package item.sprignboot.service;


import item.domain.common.ItemStatusDTO;
import item.domain.common.ItemType;
import item.domain.item.ItemRepositoryProvider;
import item.domain.item.ItemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ItemServiceImpl extends ItemService {

    public ItemServiceImpl(ItemRepositoryProvider itemRepositoryProvider) {
        super(itemRepositoryProvider);
    }

    @Override
    public ItemStatusDTO addAvailableStock(ItemType itemType, Long itemId, Long quantity) {
        return super.addAvailableStock(itemType, itemId, quantity);
    }

    @Override
    public ItemStatusDTO removeAvailableStock(ItemType itemType, Long itemId, Long quantity) {
        return super.removeAvailableStock(itemType, itemId, quantity);
    }

    @Override
    public ItemStatusDTO getItemStatus(ItemType itemType, Long itemId) {
        return super.getItemStatus(itemType, itemId);
    }
}
