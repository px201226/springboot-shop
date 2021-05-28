package com.alethio.service.service.domain.item;

import java.util.Optional;

public interface IItemRepository {

    public Optional<? extends Item> findById(Long id);

}
