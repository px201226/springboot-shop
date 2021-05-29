package com.alethio.service.service.springboot.repository;


import com.alethio.service.service.domain.item.IItemRepository;
import com.alethio.service.service.domain.item.ItemEntity;
import com.alethio.service.service.springboot.repository.jpa.IFoodJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@RequiredArgsConstructor
@Repository
public class FoodRepository implements IItemRepository {

    private final IFoodJpaRepository iFoodJpaRepository;

    @Override
    public Optional<? extends ItemEntity> findById(Long id) {
        return iFoodJpaRepository.findById(id);
    }
}
