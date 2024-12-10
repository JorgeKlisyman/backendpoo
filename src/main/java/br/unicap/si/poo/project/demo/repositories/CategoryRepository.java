package br.unicap.si.poo.project.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
//import com.projetofinalback.demo.models.Product;
import br.unicap.si.poo.project.demo.models.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{


       // public void addProductToCategory(Product productId, Category categoryId);// é para adicionar um produto a uma categoria


}
