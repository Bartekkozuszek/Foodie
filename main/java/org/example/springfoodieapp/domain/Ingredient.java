package org.example.springfoodieapp.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(exclude = {"recipe"})
@Entity
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private BigDecimal amount;

    @ManyToOne
    private Recipe recipe;

    public Ingredient() {
    }


    public Ingredient(String description, BigDecimal amount) {
        this.description = description;
        this.amount = amount;
    }

}
