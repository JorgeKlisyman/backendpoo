package  br.unicap.si.poo.project.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.unicap.si.poo.project.demo.models.Promotion;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Long> {

}
