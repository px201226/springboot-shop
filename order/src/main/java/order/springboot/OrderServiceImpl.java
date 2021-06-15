package order.springboot;


import order.domain.order.IOrderRepository;
import order.domain.order.OrderService;
import order.springboot.remote.IStockRemoteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderServiceImpl extends OrderService {

    public OrderServiceImpl(IOrderRepository orderRepository, IStockRemoteService stockService){
        super(orderRepository,stockService);
    }
}
