package org.example.springfoodieapp.commands;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review {
    private Long reviewId;
    private Double rating;
    private RecipeCommand recipeCommand;

}
