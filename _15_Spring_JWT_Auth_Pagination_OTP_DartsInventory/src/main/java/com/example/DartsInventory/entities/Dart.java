package com.example.DartsInventory.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Dart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer dartsId;

    @Column(nullable = false, length = 250)
    @NotBlank(message = "Please provide the dart")
    private String dart;

    @Column(nullable = false)
    @NotBlank(message = "Please provide the material")
    private String material;

    @Column(nullable = false)
    private Integer materialPercantage;

    @ElementCollection
    @CollectionTable(name = "weights")
    private Set<Integer> weights;

    @Column(nullable = false)
    @NotBlank(message = "Please provide the material brand")
    private String brand;

    @Column(nullable = false)
    @NotBlank(message = "Please provide the darts image")
    private String image;
}
