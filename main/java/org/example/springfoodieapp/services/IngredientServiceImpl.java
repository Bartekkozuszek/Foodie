package org.example.springfoodieapp.services;

import org.example.springfoodieapp.commands.IngredientCommand;
import org.example.springfoodieapp.converts.IngredientCommandToIngredient;
import org.example.springfoodieapp.converts.IngredientToIngredientCommand;
import org.example.springfoodieapp.domain.Ingredient;
import org.example.springfoodieapp.domain.Recipe;
import org.example.springfoodieapp.repositories.RecipeRepository;

import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final RecipeRepository recipeRepository;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;

    public IngredientServiceImpl(IngredientToIngredientCommand ingredientToIngredientCommand, RecipeRepository recipeRepository, IngredientCommandToIngredient ingredientCommandToIngredient) {
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.recipeRepository = recipeRepository;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
    }

    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

        if(!recipeOptional.isPresent()) {
            System.out.println("error: recipe id not found");
        }

        Recipe recipe = recipeOptional.get();
        Optional<IngredientCommand> ingredientCommandOptional = recipe.getIngredients()
                .stream().filter(ingredient -> ingredient.getId().equals(ingredientId))
                .map(ingredient -> ingredientToIngredientCommand.convert(ingredient)).findFirst();
        if(!ingredientCommandOptional.isPresent()) {
            System.out.println("error: ingredient id not found");
        }

        return ingredientCommandOptional.get();

    }

    @Override
    public IngredientCommand saveIngredientCommand(IngredientCommand command) {
        System.out.println(command.getId());
        Optional<Recipe> recipeOptional = recipeRepository.findById(command.getRecipeId());
        if(!recipeOptional.isPresent()) {
            return new IngredientCommand();
        } else {
            Recipe recipe = recipeOptional.get();
            Optional<Ingredient> ingredientOptional = recipe
                    .getIngredients()
                    .stream()
                    .filter(ingredient -> ingredient.getId().equals(command.getId()))
                    .findFirst();
            if(ingredientOptional.isPresent()) {
                Ingredient ingredientFound = ingredientOptional.get();
                ingredientFound.setDescription(command.getDescription());
                ingredientFound.setAmount(command.getAmount());

            } else {
                //add new Ingredient
                Ingredient ingredient = ingredientCommandToIngredient.convert(command);
                ingredient.setRecipe(recipe);
                recipe.addIngredient(ingredient);
            }

            Recipe savedRecipe = recipeRepository.save(recipe);

            Optional<Ingredient> savedIngredientOptional = savedRecipe.getIngredients().stream()
                    .filter(recipeIngredients -> recipeIngredients.getId().equals(command.getId()))
                    .findFirst();

            //check by description
            if(!savedIngredientOptional.isPresent()){
                savedIngredientOptional = savedRecipe.getIngredients().stream()
                        .filter(recipeIngredients -> recipeIngredients.getDescription().equals(command.getDescription()))
                        .filter(recipeIngredients -> recipeIngredients.getAmount().equals(command.getAmount()))
                        .findFirst();
            }

            //to do check for fail
            return ingredientToIngredientCommand.convert(savedIngredientOptional.get());
        }
    }

    @Override
    public void deleteById(Long recipeId, Long id) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

        if(recipeOptional.isPresent()) {
            Recipe recipe = recipeOptional.get();
            Optional<Ingredient> ingredientOptional = recipe
                    .getIngredients()
                    .stream()
                    .filter(ingredient -> ingredient.getId().equals(id))
                    .findFirst();
            if(ingredientOptional.isPresent()) {
                Ingredient ingredientDelete = ingredientOptional.get();
                ingredientDelete.setRecipe(null);
                recipe.getIngredients().remove(ingredientOptional.get());
                recipeRepository.save(recipe);
            }
        } else {
            System.out.println("no recipe");
        }
    }
}
