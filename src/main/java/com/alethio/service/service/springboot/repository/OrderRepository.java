package com.alethio.service.service.springboot.repository;


import com.alethio.service.service.domain.order.IOrderRepository;
import com.alethio.service.service.domain.order.Order;
import com.alethio.service.service.springboot.repository.jpa.IOrderJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


@RequiredArgsConstructor
@Repository
public class OrderRepository implements IOrderRepository {

    private final IOrderJpaRepository iOrderJpaRepository;

    @Override
    public Order save(Order order) {
        return iOrderJpaRepository.save(order);
    }
}
