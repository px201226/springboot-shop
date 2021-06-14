package order.springboot.repository;


import order.domain.order.IOrderRepository;
import order.domain.order.OrderEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class OrderRepositoryImpl implements IOrderRepository {

    private final IOrderJpaRepository iOrderJpaRepository;

    @Override
    public OrderEntity save(OrderEntity orderEntity) {
        return iOrderJpaRepository.save(orderEntity);
    }
}
