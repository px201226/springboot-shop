package stock.springboot.stock;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import stock.domain.common.ItemStatusDTO;
import stock.domain.common.ItemType;
import stock.domain.stock.StockService;
import stock.domain.stock.request.IReceivingRequestRepository;
import stock.springboot.remote.IItemRemoteService;

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
