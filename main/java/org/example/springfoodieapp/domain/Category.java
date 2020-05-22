package org.example.springfoodieapp.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(exclude = {"recipes"})
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

   /* @ManyToMany(mappedBy = "categories")
    private Set<Recipe> recipes = new HashSet<>();*/

   @ManyToOne
   private Recipe recipes;

    public Category() {}

    public Category(Long id, String description) {
        this.id = id;
        this.description = description;
    }
}
