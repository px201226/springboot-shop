package com.alethio.service.service.domain.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderJpaRepository extends JpaRepository<Order, Long> {
}
