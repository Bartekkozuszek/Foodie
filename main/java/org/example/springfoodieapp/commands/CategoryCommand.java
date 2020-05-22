package org.example.springfoodieapp.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryCommand {
    private Long id;
    private Long recipeId;
    private String description;
}
