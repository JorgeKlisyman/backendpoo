package br.unicap.si.poo.project.demo.models;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "lojas_parceiras")
public class PartnerStore implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PartnerStore_ID")
    private Long partnerStoreId;

    @Column(name = "PartnerStore_NAME", nullable = false)
    private String partnerStoreName;

    @Column(name = "PartnerStore_URL", nullable = false, unique = true)
    private String partnerStoreUrl;

    // não podem existir duas lojas parceiras iguais
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        PartnerStore partnerStore = (PartnerStore) o;
        return Objects.equals(partnerStoreId, partnerStore.partnerStoreId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(partnerStoreId);
    }
}
