package br.unicap.si.poo.project.demo.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    @NotBlank
    @Size(max = 100)
    private String username;

    @NotBlank
    private String email;

    @NotBlank
    @Size(max = 200)
    private String password;
}
