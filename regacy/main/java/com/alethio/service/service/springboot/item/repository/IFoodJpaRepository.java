package com.alethio.service.service.springboot.item.repository;

import com.alethio.service.service.domain.item.ItemEntity;
import com.alethio.service.service.domain.item.itemtype.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.Optional;

@Repository
public interface IFoodJpaRepository extends JpaRepository<Food, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    public Optional<Food> findById(Long id);
}
