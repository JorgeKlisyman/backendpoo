package br.unicap.si.poo.project.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.unicap.si.poo.project.demo.models.Product;
import br.unicap.si.poo.project.demo.models.PartnerStore;
import br.unicap.si.poo.project.demo.models.Category;  // Certifique-se de importar a classe Category

import jakarta.transaction.Transactional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
    @Transactional
    void deleteByProductCategory(Category category);  // Alterado para aceitar Category ao invés de String

    @Transactional
    void deleteBySaleBy(PartnerStore partnerStore);
}
