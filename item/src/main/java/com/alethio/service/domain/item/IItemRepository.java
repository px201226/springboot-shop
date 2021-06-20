package com.alethio.service.domain.item;

import java.util.Optional;

public interface IItemRepository<T extends ItemEntity> {

    public Optional<T> findById(Long id);

    public T save(T t);
}
