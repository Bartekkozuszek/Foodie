package org.example.springfoodieapp.converts;

import lombok.Synchronized;
import org.example.springfoodieapp.commands.CategoryCommand;
import org.example.springfoodieapp.domain.Category;
import org.example.springfoodieapp.domain.Ingredient;
import org.example.springfoodieapp.domain.Recipe;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class CategoryCommandToCategory implements Converter<CategoryCommand, Category> {

    @Synchronized
    @Nullable
    @Override
    public Category convert(CategoryCommand source) {
        if (source == null) {
            return null;
        }

        final Category category = new Category();
        category.setId(source.getId());
        if (source.getRecipeId() != null) {
            Recipe recipe = new Recipe();
            recipe.setId(source.getRecipeId());
            category.setRecipes(recipe);
            recipe.addCategory(category);
        }
        category.setDescription(source.getDescription());
        return category;
       /* category.setId(source.getId());
        category.setDescription(source.getDescription());
        return category;*/
    }
}
