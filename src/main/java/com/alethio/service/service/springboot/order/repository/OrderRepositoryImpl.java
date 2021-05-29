package com.alethio.service.service.springboot.order;


import com.alethio.service.service.domain.order.IOrderRepository;
import com.alethio.service.service.domain.order.OrderEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


@RequiredArgsConstructor
@Repository
public class OrderRepository implements IOrderRepository {

    private final IOrderJpaRepository iOrderJpaRepository;

    @Override
    public OrderEntity save(OrderEntity orderEntity) {
        return iOrderJpaRepository.save(orderEntity);
    }
}
