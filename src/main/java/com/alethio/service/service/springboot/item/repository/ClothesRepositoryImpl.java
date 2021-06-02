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
public class ClothesRepositoryImpl implements IItemRepository<Clothes> {

    private final IClothesJpaRepository iClothesJpaRepository;

    @Override
    public Optional<Clothes> findById(Long id) {
        return iClothesJpaRepository.findById(id);
    }

    @Override
    public Clothes save(Clothes clothes) {
        return iClothesJpaRepository.save(clothes);
    }

}
