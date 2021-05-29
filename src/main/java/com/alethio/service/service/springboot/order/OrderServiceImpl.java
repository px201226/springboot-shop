package com.alethio.service.service.springboot.order.service;


import com.alethio.service.service.domain.order.IOrderRepository;
import com.alethio.service.service.domain.order.OrderService;
import com.alethio.service.service.domain.stock.StockService;
import org.springframework.stereotype.Service;


@Service
public class OrderServiceImpl extends OrderService {

    public OrderServiceImpl(IOrderRepository orderRepository, StockService stockService){
        super(orderRepository,stockService);
    }
}
