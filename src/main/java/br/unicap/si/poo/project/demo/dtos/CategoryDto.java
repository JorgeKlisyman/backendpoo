package br.unicap.si.poo.project.demo.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CategoryDto {

    @NotBlank
    @Size(max=25)
    private String categoryName;

}
