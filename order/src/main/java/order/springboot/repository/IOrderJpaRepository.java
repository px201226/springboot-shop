package order.springboot.repository;


import order.domain.order.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderJpaRepository extends JpaRepository<OrderEntity, Long> {
}
