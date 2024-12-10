package br.unicap.si.poo.project.demo.models;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "Avaliacoes")
public class Rating implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "rating_ID", unique = true)
    private Long ratingId;

    @Column(name = "rating_comment", nullable = false)
    private String ratingComment;

    @Column(name = "date_rating")
    private LocalDate creationDate;

    @ManyToOne
    @JoinColumn(name = "rating_by", referencedColumnName = "id", nullable = false) // avaliado por
    private User userId;

    @ManyToOne
    @JoinColumn(name = "partner", referencedColumnName = "PartnerStore_ID", nullable = false) // loja avaliada
    private PartnerStore partnerId;

}
