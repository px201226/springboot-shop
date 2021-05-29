package com.alethio.service.service.springboot.order;

import com.alethio.service.service.domain.item.category.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFoodJpaRepository extends JpaRepository<Food, Long> {
}
