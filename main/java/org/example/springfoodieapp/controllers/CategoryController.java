package org.example.springfoodieapp.controllers;

import org.example.springfoodieapp.commands.CategoryCommand;
import org.example.springfoodieapp.commands.RecipeCommand;
import org.example.springfoodieapp.services.CategoryService;
import org.example.springfoodieapp.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CategoryController {

    private final RecipeService recipeService;
    private final CategoryService categoryService;

    public CategoryController(RecipeService recipeService, CategoryService categoryService) {
        this.categoryService = categoryService;
        this.recipeService = recipeService;
    }

    @GetMapping
    @RequestMapping("recipe/{recipeId}/categories")
    public String listCategories(@PathVariable String recipeId, Model model) {
        model.addAttribute("recipe", recipeService.findCommandById(recipeId));
        return "recipe/category/list";
    }

    @GetMapping
    @RequestMapping("recipe/{recipeId}/category/{id}/show")
    public String showRecipeCategory(@PathVariable String recipeId,
                                       @PathVariable String id, Model model) {
        model.addAttribute("category", categoryService.findByRecipeAndCategoryId(
                Long.valueOf(recipeId), Long.valueOf(id)));
        return "recipe/category/show";
    }

    @PostMapping
    @RequestMapping("recipe/{recipeId}/category")
    public String saveOrUpdate(@ModelAttribute CategoryCommand categoryCommand) {
        System.out.println("------------" + categoryCommand.getRecipeId() + "-------------");
        CategoryCommand savedCommand = categoryService.saveCategoryCommand(categoryCommand);
        return "redirect:/recipe/" + savedCommand.getRecipeId() + "/category/" + savedCommand.getId() +"/show";
    }

    @GetMapping
    @RequestMapping("recipe/{recipeId}/category/new")
    public String newRecipe(@PathVariable String recipeId, Model model) {
        RecipeCommand recipeCommand = recipeService.findCommandById(recipeId);
        CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setRecipeId(Long.valueOf(recipeId));
        model.addAttribute("category", categoryCommand);

        return "recipe/category/categoryform";
    }

    @GetMapping
    @RequestMapping("recipe/{recipeId}/category/{id}/update")
    public String updateRecipeCategory(@PathVariable String recipeId,
                                         @PathVariable String id, Model model){
        model.addAttribute("category", categoryService.findByRecipeAndCategoryId(Long.valueOf(recipeId), Long.valueOf(id)));

        return "recipe/category/categoryform";
    }

    @GetMapping
    @RequestMapping("recipe/{recipeId}/category/{id}/delete")
    public String deleteIngredient(@PathVariable String recipeId,
                                   @PathVariable String id) {
        categoryService.deleteById(Long.valueOf(recipeId), Long.valueOf(id));
        return "redirect:/recipe/" + recipeId +"/categories";
    }
}
