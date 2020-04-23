package org.example.springfoodieapp.repositories;

import org.example.springfoodieapp.domain.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;


import java.util.Optional;


public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {
    public abstract Optional<UnitOfMeasure> findByDescription(String description);
}
