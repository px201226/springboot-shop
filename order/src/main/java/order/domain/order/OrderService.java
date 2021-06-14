package order.domain.order;


import order.domain.common.ItemType;
import order.springboot.IStockService;

public class OrderService implements IOrderService {

    private IOrderRepository orderRepository;
    private IStockService stockService;

    public OrderService(IOrderRepository orderRepository, IStockService stockService) {
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
