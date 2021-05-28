package com.alethio.service.service.springboot.repository;


import com.alethio.service.service.domain.item.IItemRepository;
import com.alethio.service.service.domain.item.Item;
import com.alethio.service.service.springboot.repository.jpa.IClothesJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@RequiredArgsConstructor
@Repository
public class ClothesRepository implements IItemRepository {

    private final IClothesJpaRepository iClothesJpaRepository;

    @Override
    public Optional<? extends Item> findById(Long id) {
        return iClothesJpaRepository.findById(id);
    }
}
