package org.example.springfoodieapp.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;
    private Integer servings;
    private String source;
    private Integer cookTime;
    private Integer prepTime;
    private String description;
    @Lob
    private String directions;

    @Lob
    private Byte[] image;

    @Enumerated(value = EnumType.STRING)
    private Tags tags;

    @OneToOne(cascade = CascadeType.ALL)
    private Notes notes;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "recipes")
    private Set<Category> categories = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "recipe")
    private Set<Ingredient> ingredients = new HashSet<>();

    @OneToMany
    private Set<Review> reviews = new HashSet<>();

    public void setNotes(Notes notes) {
        notes.setRecipe(this);
        this.notes = notes;
    }

    public Recipe addIngredient(Ingredient ingredient) {
        ingredient.setRecipe(this);
        this.ingredients.add(ingredient);
        return this;
    }

    public Recipe addCategory(Category category) {
        category.setRecipes((Recipe) this);
        this.categories.add(category);
        return this;
    }
}
