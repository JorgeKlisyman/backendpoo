package br.unicap.si.poo.project.demo.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.unicap.si.poo.project.demo.models.Promotion;
import br.unicap.si.poo.project.demo.repositories.PromotionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PromotionService {

    private final PromotionRepository promotionRepository;

      //método que cria uma promoção
    @Transactional
    public Promotion save(Promotion promotion) {
        return promotionRepository.save(promotion);
    }
    @Transactional(readOnly=true)
    public Promotion searchById(Long idpromotion) {
        return promotionRepository.findById(idpromotion).orElseThrow(() -> new RuntimeException("A promoção não foi encontrado ou não existe mais."));
    }

    //método para alterar o nome de alguma promoção
    @Transactional
    public Promotion updatePromotionName(Long idpromotion, String namepromotion) {
        Promotion promocao = searchById(idpromotion);
        promocao.setPromotionName(namepromotion);
        return promotionRepository.save(promocao);
    }
    //método para alterar a descrição de alguma promoção
    @Transactional
    public Promotion updatePromotionDescription(Long idpromotion, String descriptionpromotion) {
        Promotion promocao = searchById(idpromotion);
        promocao.setPromotionDescription(descriptionpromotion);
        return promotionRepository.save(promocao);
    }
        //método para alterar o desconto de alguma promoção
        @Transactional
        public Promotion updatePromotionDiscount(Long idpromotion, double discount) {
            Promotion promocao = searchById(idpromotion);
            promocao.setPromotiontDiscount(discount);
            return promotionRepository.save(promocao);
        }

    @Transactional(readOnly = true)
    public List<Promotion> searchAll() {
        return promotionRepository.findAll();
    }
    //método para deletar uma promoção
    @Transactional
    public String deletePromotion(Long idpromotion){
        if (!promotionRepository.existsById(idpromotion)){
            throw new RuntimeException ("Não foi possível deletar essa promoção. ERRO!!!");
        }
        else{   promotionRepository.deleteById(idpromotion);
        return "Promoção deletada com sucesso.";}
     
    }
}
