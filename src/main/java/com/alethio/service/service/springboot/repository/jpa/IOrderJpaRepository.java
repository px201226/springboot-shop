package com.alethio.service.service.springboot.repository.jpa;

import com.alethio.service.service.domain.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderJpaRepository extends JpaRepository<Order, Long> {
}
