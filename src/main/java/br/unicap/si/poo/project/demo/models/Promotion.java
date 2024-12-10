package  br.unicap.si.poo.project.demo.models;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "promotion")
public class Promotion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "promotion_ID")
    private Long promotionId;

    @Column(name = "promotion_NAME", nullable = false)
    private String promotionName;

    @Column(name = "promotion_DESCRIPTION", nullable = false)
    private String promotionDescription;

    @Column(name = "promotion_DISCOUNT", nullable = false, unique = true)
    private double promotiontDiscount;

    @Column(name = "creation_date")
    private LocalDate creationDate;
}
