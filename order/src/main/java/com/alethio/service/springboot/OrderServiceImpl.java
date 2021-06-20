package com.alethio.service.springboot;


import com.alethio.service.domain.order.IOrderRepository;
import com.alethio.service.domain.order.OrderService;
import com.alethio.service.springboot.remote.IStockRemoteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderServiceImpl extends OrderService {

    public OrderServiceImpl(IOrderRepository orderRepository, IStockRemoteService stockService){
        super(orderRepository,stockService);
    }
}
