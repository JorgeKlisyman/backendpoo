package br.unicap.si.poo.project.demo.models;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "Produtos")

public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_ID")
    private Long productId;

    @Lob
    @Column(name = "product_IMAGE", nullable = true)
    private byte[] productImage;

    @Column(name = "product_NAME", nullable = false)
    private String productName;

    @Column(name = "product_DESCRIPTION", nullable = false)
    private String productDescription;

    @Column(name = "product_PRICE", nullable = false)
    private double productPrice;

    @ManyToOne
    @JoinColumn(name = "product_category", nullable = false, referencedColumnName = "categoryID")
    private Category productCategory;

    @ManyToOne
    @JoinColumn(name = "sale_by", nullable = false, referencedColumnName = "PartnerStore_ID")
    private PartnerStore saleBy;

}
