package org.example.springfoodieapp.repositories;

import org.example.springfoodieapp.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
