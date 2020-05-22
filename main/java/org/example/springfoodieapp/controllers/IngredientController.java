package org.example.springfoodieapp.controllers;

import org.example.springfoodieapp.commands.IngredientCommand;
import org.example.springfoodieapp.commands.RecipeCommand;

import org.example.springfoodieapp.services.IngredientService;
import org.example.springfoodieapp.services.RecipeService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class IngredientController {

    private final RecipeService recipeService;
    private final IngredientService ingredientService;

    public IngredientController(RecipeService recipeService, IngredientService ingredientService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
    }

    @GetMapping
    @RequestMapping("recipe/{recipeId}/ingredients")
    public String listIngredients(@PathVariable String recipeId, Model model) {
        model.addAttribute("recipe", recipeService.findCommandById(recipeId));
        return "recipe/ingredient/list";
    }

    @GetMapping
    @RequestMapping("recipe/{recipeId}/ingredient/{id}/show")
    public String showRecipeIngredient(@PathVariable String recipeId,
                                       @PathVariable String id, Model model) {
        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(
                Long.valueOf(recipeId), Long.valueOf(id)));
        return "recipe/ingredient/show";
    }

    @PostMapping
    @RequestMapping("recipe/{recipeId}/ingredient")
    public String saveOrUpdate(@ModelAttribute IngredientCommand ingredientCommand) {
        System.out.println("------------" + ingredientCommand.getRecipeId() + "-------------");
        IngredientCommand savedCommand = ingredientService.saveIngredientCommand(ingredientCommand);
        return "redirect:/recipe/" + savedCommand.getRecipeId() + "/ingredient/" + savedCommand.getId() +"/show";
    }

    @GetMapping
    @RequestMapping("recipe/{recipeId}/ingredient/new")
    public String newRecipe(@PathVariable String recipeId, Model model) {
        RecipeCommand recipeCommand = recipeService.findCommandById(recipeId);
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setRecipeId(Long.valueOf(recipeId));
        model.addAttribute("ingredient", ingredientCommand);

        return "recipe/ingredient/ingredientform";

    }

    @GetMapping
    @RequestMapping("recipe/{recipeId}/ingredient/{id}/update")
    public String updateRecipeIngredient(@PathVariable String recipeId,
                                         @PathVariable String id, Model model){
        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(id)));

        return "recipe/ingredient/ingredientform";
    }

    @GetMapping
    @RequestMapping("recipe/{recipeId}/ingredient/{id}/delete")
    public String deleteIngredient(@PathVariable String recipeId,
                                   @PathVariable String id) {
        ingredientService.deleteById(Long.valueOf(recipeId), Long.valueOf(id));
        return "redirect:/recipe/" + recipeId +"/ingredients";
    }
}
