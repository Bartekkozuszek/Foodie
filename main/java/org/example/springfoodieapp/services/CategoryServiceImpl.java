package org.example.springfoodieapp.services;

import lombok.extern.slf4j.Slf4j;
import org.example.springfoodieapp.commands.CategoryCommand;
import org.example.springfoodieapp.converts.CategoryCommandToCategory;
import org.example.springfoodieapp.converts.CategoryToCategoryCommand;
import org.example.springfoodieapp.domain.Category;
import org.example.springfoodieapp.domain.Recipe;
import org.example.springfoodieapp.repositories.CategoryRepository;
import org.example.springfoodieapp.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryToCategoryCommand categoryToCategoryCommand;
    private final CategoryCommandToCategory categoryCommandToCategory;
    private final RecipeRepository recipeRepository;
    private final CategoryRepository categoryRepository;


    public CategoryServiceImpl(CategoryCommandToCategory categoryCommandToCategory, CategoryToCategoryCommand categoryToCategoryCommand, RecipeRepository recipeRepository, CategoryRepository categoryRepository ){
        this.recipeRepository = recipeRepository;
        this.categoryToCategoryCommand = categoryToCategoryCommand;
        this.categoryCommandToCategory = categoryCommandToCategory;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryCommand findByRecipeAndCategoryId(Long recipeId, Long categoryId) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

        if(!recipeOptional.isPresent()) {
            System.out.println("error: recipe id not found");
        }

        Recipe recipe = recipeOptional.get();
        Optional<CategoryCommand> categoryCommandOptional = recipe.getCategories()
                .stream().filter(category -> category.getId().equals(categoryId))
                .map(category -> categoryToCategoryCommand.convert(category)).findFirst();

        if(!categoryCommandOptional.isPresent()) {
            System.out.println("error: category not found");
        }

        return categoryCommandOptional.get();
     }

     @Override
     public CategoryCommand saveCategoryCommand(CategoryCommand command) {
         System.out.println(command.getId());
         Optional<Recipe> recipeOptional = recipeRepository.findById(command.getRecipeId());
         if(!recipeOptional.isPresent()) {
             return new CategoryCommand();
         } else {
             Recipe recipe = recipeOptional.get();
             Optional<Category> categoryOptional = recipe
                     .getCategories()
                     .stream()
                     .filter(category -> category.getId().equals(command.getId()))
                     .findFirst();
             if(categoryOptional.isPresent()) {
                 Category categoryFound = categoryOptional.get();
                 categoryFound.setDescription(command.getDescription());

             } else {
                 Category category = categoryCommandToCategory.convert(command);
                 category.setRecipes(recipe);
                 recipe.addCategory(category);
             }

             Recipe savedRecipe = recipeRepository.save(recipe);

             Optional<Category> savedCategoryOptional = savedRecipe.getCategories().stream()
                     .filter(recipeCategories -> recipeCategories.getDescription().equals(command.getDescription()))
                     .findFirst();

             if(!savedCategoryOptional.isPresent()) {
                 savedCategoryOptional = savedRecipe.getCategories().stream()
                         .filter(recipeCategories -> recipeCategories.getDescription().equals(command.getDescription()))
                         .findFirst();
             }

             return categoryToCategoryCommand.convert(savedCategoryOptional.get());
         }
    }

    @Override
    public void deleteById(Long recipeId, Long id) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

        if(recipeOptional.isPresent()) {
            Recipe recipe = recipeOptional.get();
            Optional<Category> categoryOptional = recipe
                    .getCategories()
                    .stream()
                    .filter(category -> category.getId().equals(id))
                    .findFirst();
            if(categoryOptional.isPresent()) {
                Category categoryDelete = categoryOptional.get();
                categoryDelete.setRecipes(null);
                recipe.getCategories().remove(categoryOptional.get());
                recipeRepository.save(recipe);
            }
        }   else {
            System.out.println("no category");
        }
    }
    @Override
    public Set<Category> getCategories() {
        Set<Category> categorySet = new HashSet<>();
        categoryRepository.findAll().iterator().forEachRemaining(categorySet::add);
        return categorySet;
    }
}
