package  br.unicap.si.poo.project.demo.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Setter
@Getter

public class PromotionDto {

    @NotBlank
    private String promotionName;

    @NotBlank
    private String promotionDescription;

    @NotBlank
    private double promotiontDiscount;

}
