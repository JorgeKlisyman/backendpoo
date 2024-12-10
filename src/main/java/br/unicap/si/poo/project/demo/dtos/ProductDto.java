package  br.unicap.si.poo.project.demo.dtos;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ProductDto {

    private String productImage;

    @NotBlank
    private String productName;

    @NotBlank
    private String productDescription;

    @NotBlank
    private double productPrice;

    @NotBlank
    private Long productCategory;

    @NotBlank
    private Long saleBy;
}
