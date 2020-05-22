package org.example.springfoodieapp.converts;

import lombok.Synchronized;
import org.example.springfoodieapp.commands.CategoryCommand;
import org.example.springfoodieapp.commands.IngredientCommand;
import org.example.springfoodieapp.domain.Category;
import org.example.springfoodieapp.domain.Recipe;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class CategoryToCategoryCommand implements Converter<Category, CategoryCommand> {

    @Synchronized
    @Nullable
    @Override
    public CategoryCommand convert(Category category) {
        if (category == null) {
            return null;
        }

        CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setId(category.getId());
        categoryCommand.setRecipeId(category.getRecipes().getId());
        categoryCommand.setDescription(category.getDescription());
        return categoryCommand;

     /*   categoryCommand.setId(source.getId());
        categoryCommand.setDescription(source.getDescription());

        return categoryCommand;*/
    }
}

