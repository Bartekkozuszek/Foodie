package org.example.springfoodieapp.domain;

import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "reviews")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review {

    @Id
    @Column(name = "reviewId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private Recipe recipeId;

    @OneToMany
    private Set<Review> reviews = new HashSet<>();

    public double getRating() { return calculateRatingAverage(this.id); }

    private Double rating;

    @Transient
    private Double calculateRatingAverage(Long id) {
        List<Double> sum = new ArrayList<>();
        assert reviews != null;
        for (Review r: reviews) {
            if (r.getRating() == id) {
                sum.add(r.getRating());
            }
        }
        OptionalDouble average = sum.stream().mapToDouble(a -> a).average();
        return average.isPresent() ? average.getAsDouble() : 0;
    }
}
