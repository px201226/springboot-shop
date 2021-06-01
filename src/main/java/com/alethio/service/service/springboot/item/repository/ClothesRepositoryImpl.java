package com.alethio.service.service.springboot.item.repository;


import com.alethio.service.service.domain.item.IItemRepository;
import com.alethio.service.service.domain.item.ItemEntity;
import com.alethio.service.service.domain.item.itemtype.Clothes;
import com.alethio.service.service.domain.item.itemtype.Food;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@RequiredArgsConstructor
@Repository
public class ClothesRepositoryImpl implements IItemRepository {

    private final IClothesJpaRepository iClothesJpaRepository;

    @Override
    public Optional<? extends ItemEntity> findById(Long id) {
        return iClothesJpaRepository.findById(id);
    }

    @Override
    public <T extends ItemEntity> T save(ItemEntity t) {
        Clothes save = iClothesJpaRepository.save((Clothes) t);
        return (T) save;
    }
}
