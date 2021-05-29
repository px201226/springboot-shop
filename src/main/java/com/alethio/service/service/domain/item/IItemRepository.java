package com.alethio.service.service.domain.item;

import java.util.Optional;

public interface IItemRepository {

    public <T extends ItemEntity> Optional<T> findById(Long id);

}
