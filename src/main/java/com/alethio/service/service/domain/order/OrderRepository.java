package com.alethio.service.service.domain.order;


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
