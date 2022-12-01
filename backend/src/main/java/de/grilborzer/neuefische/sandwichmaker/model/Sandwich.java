package de.grilborzer.neuefische.sandwichmaker.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@With
public class Sandwich {
    private String id;
    private String name;
    private String patty;
    private int numberOfPatties;
    private boolean isGrilled;
    private String extraWishes;
}
