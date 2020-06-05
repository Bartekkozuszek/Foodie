package org.example.springfoodieapp.converts;

import org.example.springfoodieapp.commands.IngredientCommand;
import org.example.springfoodieapp.domain.Ingredient;
import org.springframework.stereotype.Component;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

@Component
public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand> {

    @Synchronized
    @Nullable
    @Override
    public IngredientCommand convert(Ingredient ingredient) {
        if (ingredient == null) {
            return null;
        }

        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(ingredient.getId());
        ingredientCommand.setRecipeId(ingredient.getRecipe().getId());
        ingredientCommand.setAmount(ingredient.getAmount());
        ingredientCommand.setDescription(ingredient.getDescription());
        ingredientCommand.setUnit(ingredient.getUnit());
        return ingredientCommand;
    }
}
