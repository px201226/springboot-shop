package com.alethio.service.service.springboot.order;


import com.alethio.service.service.domain.order.IOrderRepository;
import com.alethio.service.service.domain.order.OrderService;
import com.alethio.service.service.domain.stock.StockService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class OrderServiceImpl extends OrderService {

    public OrderServiceImpl(IOrderRepository orderRepository, StockService stockService){
        super(orderRepository,stockService);
    }
}
