package com.alethio.service.service.springboot.item.repository;


import com.alethio.service.service.domain.item.IItemRepository;
import com.alethio.service.service.domain.item.ItemEntity;
import com.alethio.service.service.domain.item.itemtype.Food;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@RequiredArgsConstructor
@Repository
public class FoodRepositoryImpl implements IItemRepository {

    private final IFoodJpaRepository iFoodJpaRepository;

    @Override
    public Optional<? extends ItemEntity> findById(Long id) {
        return iFoodJpaRepository.findById(id);
    }

    @Override
    public <T extends ItemEntity> T save(ItemEntity t) {
        Food save = iFoodJpaRepository.save((Food) t);
        return (T) save;
    }
}
