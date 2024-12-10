package br.unicap.si.poo.project.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.unicap.si.poo.project.demo.models.PartnerStore;

@Repository
public interface PartnerStoreRepository extends JpaRepository<PartnerStore, Long> {
    // Métodos customizados de consulta podem ser adicionados aqui, se necessário
}
