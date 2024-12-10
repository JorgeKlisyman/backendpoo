package br.unicap.si.poo.project.demo.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
public class RatingDto {

    @NotBlank
    private String ratingComment;

    @NotBlank
    private Long userId;

    @NotBlank
    private Long partnerId;

}
