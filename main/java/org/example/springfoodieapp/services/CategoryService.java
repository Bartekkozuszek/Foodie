package org.example.springfoodieapp.services;


import org.example.springfoodieapp.commands.CategoryCommand;
import org.example.springfoodieapp.domain.Category;
import org.example.springfoodieapp.domain.Recipe;

import java.util.Set;

public interface CategoryService {
    CategoryCommand findByRecipeAndCategoryId(Long recipeId, Long categoryId);
    public CategoryCommand saveCategoryCommand(CategoryCommand command);
    public void deleteById(Long recipeId, Long id);

    Set<Category> getCategories();
}
