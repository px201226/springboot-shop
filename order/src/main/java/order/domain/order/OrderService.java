package order.domain.order;


import order.domain.common.ItemType;
import order.springboot.remote.IStockRemoteService;

public class OrderService implements IOrderService {

    private IOrderRepository orderRepository;
    private IStockRemoteService stockService;

    public OrderService(IOrderRepository orderRepository, IStockRemoteService stockService) {
        this.orderRepository = orderRepository;
        this.stockService = stockService;
    }

    @Override
    public OrderEntity placeOrder(PlaceOrderRequestDTO placeOrderRequestDto) {

        ItemType orderItemType = placeOrderRequestDto.getItemType();
        Long orderItemId = placeOrderRequestDto.getItemId();

        stockService.placeOrder(orderItemType,orderItemId,1L);

        return orderRepository.save(placeOrderRequestDto.toEntity());

    }
}
