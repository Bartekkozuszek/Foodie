package org.example.springfoodieapp.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.springfoodieapp.domain.Tags;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class RecipeCommand {
    private Long id;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    private Byte[] image;
    private String directions;
    private Set<IngredientCommand> ingredients = new HashSet<>();
    private Tags tags;
    private NotesCommand notes;
    private Set<CategoryCommand> categories = new HashSet<>();
}
