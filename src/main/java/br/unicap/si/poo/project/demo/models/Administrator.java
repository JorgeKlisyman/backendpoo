package br.unicap.si.poo.project.demo.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "administrators")
public class Administrator implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "administrator_id")
    private Long idAdministrator;

    @Column(name = "administrator_name", nullable = false, unique = true, length = 100)
    private String nameAdministrator;

    @Column(name = "administrator_email", nullable = false)
    private String emailAdministrator;

    @Column(name = "administrator_password", nullable = false, length = 200)
    private String passwordAdministrator;

    @Column(name = "creation_date")
    private LocalDate creationDate;

    @Column(name = "update_date")
    private LocalDate updateDate;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Administrator administrator = (Administrator) o;
        return Objects.equals(idAdministrator, administrator.idAdministrator);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idAdministrator);
    }
}
