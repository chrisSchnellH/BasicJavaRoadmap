package com.example.DartsInventory.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DartDto {

    private Integer dartsId;

    @NotBlank(message = "Please provide the dart")
    private String dart;

    @NotBlank(message = "Please provide the material")
    private String material;

    private Integer materialPercantage;

    private Set<Integer> weights;

    @NotBlank(message = "Please provide the material brand")
    private String brand;

    @NotBlank(message = "Please provide the darts image")
    private String image;

    @NotBlank(message = "Please provide the image Url")
    private String imageUrl;
}
