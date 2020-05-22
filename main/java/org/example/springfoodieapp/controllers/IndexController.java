package org.example.springfoodieapp.controllers;

import lombok.extern.slf4j.Slf4j;
import org.example.springfoodieapp.commands.CategoryCommand;
import org.example.springfoodieapp.commands.RecipeCommand;
import org.example.springfoodieapp.services.CategoryService;
import org.example.springfoodieapp.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.Id;

@Slf4j
@Controller
public class IndexController {

    private final RecipeService recipeService;
    private final CategoryService categoryService;


    public IndexController(RecipeService recipeService, CategoryService categoryService) {
        this.recipeService = recipeService;
        this.categoryService = categoryService;
    }


    @GetMapping
    @RequestMapping({"","/","/index"})
    public String getIndexPage(Model model) {
        log.debug("Getting index page");
        model.addAttribute("recipes", recipeService.getRecipes());
        model.addAttribute("category", categoryService.getCategories());
        return "index";
    }
}
