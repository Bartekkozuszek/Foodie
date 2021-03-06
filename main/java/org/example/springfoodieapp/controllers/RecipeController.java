package org.example.springfoodieapp.controllers;

import org.example.springfoodieapp.commands.RecipeCommand;
import org.example.springfoodieapp.domain.Recipe;
import org.example.springfoodieapp.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    @RequestMapping("/recipe/show/{id}")
    public String showById(@PathVariable String id, Model model) {
        Recipe recipe = recipeService.findById(Long.valueOf(id.replace(" ", "")));
        model.addAttribute("recipe", recipe);
        return "recipe/show";
    }

    @GetMapping
    @RequestMapping("/recipe/view/{id}")
    public String contById(@PathVariable String id, Model model) {
        Recipe recipe = recipeService.findById(Long.valueOf(id.replace(" ", "")));
        model.addAttribute("recipe", recipe);
        return "recipe/view";
    }


    @GetMapping
    @RequestMapping("recipe/new")
    public String newRecipe(Model model) {
        model.addAttribute("recipe", new RecipeCommand());
        return "recipe/recipeform";
    }

    @RequestMapping("recipe/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model) {
        model.addAttribute("recipe", recipeService.findCommandById(id));
        return "recipe/recipeform";
    }

    @PostMapping("recipe/save")
    public String saveOrUpdate(@ModelAttribute RecipeCommand command) {
        RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);
        System.out.println(savedCommand);
        return "redirect:/recipe/show/" + savedCommand.getId();
    }

    @GetMapping
    @RequestMapping("recipe/{id}/delete")
    public String deleteById(@PathVariable String id) {
        recipeService.deleteById(Long.valueOf(id));
        return "redirect:/";
    }


}
