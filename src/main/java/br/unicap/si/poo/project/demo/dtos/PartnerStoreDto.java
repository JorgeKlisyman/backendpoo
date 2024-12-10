package br.unicap.si.poo.project.demo.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PartnerStoreDto {

    @NotBlank
    private String partnerStoreName;

    @NotBlank
    private String partnetStoreUrl;

}
