package org.example.springfoodieapp.services;

import org.example.springfoodieapp.commands.RecipeCommand;
import org.example.springfoodieapp.domain.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();
    Recipe findById(Long l);
    RecipeCommand saveRecipeCommand(RecipeCommand command);
    RecipeCommand findCommandById(String id);
    void deleteById(Long id);
}
