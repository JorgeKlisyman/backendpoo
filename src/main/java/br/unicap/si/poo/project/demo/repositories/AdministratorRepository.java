package br.unicap.si.poo.project.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.unicap.si.poo.project.demo.models.Administrator;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Long> {

}

