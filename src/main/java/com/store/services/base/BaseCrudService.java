package com.store.services.base;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BaseCrudService<T, ID, R extends JpaRepository<T, ID>> {
    T save(T entity);

    List<T> findAll();

    void deleteById(ID id);

    Optional<T> findById(ID id);
}
