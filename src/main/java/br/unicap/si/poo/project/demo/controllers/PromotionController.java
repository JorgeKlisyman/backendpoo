package br.unicap.si.poo.project.demo.controllers;

import br.unicap.si.poo.project.demo.services.PromotionService;
import br.unicap.si.poo.project.demo.models.Promotion;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/promotions")
@CrossOrigin(origins="*")
public class PromotionController {

    private final PromotionService promotionService;

      //método para adicionar uma promoção ao sistema do banco de dados
     @PostMapping
     public ResponseEntity<Promotion> create(@RequestBody Promotion promotion){
        Promotion promocao = promotionService.save(promotion);
        return ResponseEntity.status(HttpStatus.CREATED).body(promocao);
     }
     //método para buscar uma promoção
     @GetMapping("/{idpromotion}")
     public ResponseEntity<Promotion> getById(@PathVariable Long idpromotion){
         Promotion promocao = promotionService.searchById(idpromotion);
         return ResponseEntity.ok(promocao);
     }
 
     //método para alterar o nome de uma promoção
     @PatchMapping("/{idpromotion}/updatepromotionname")
     public ResponseEntity<Promotion> updatePromotionName(@PathVariable Long idpromotion, @RequestBody Promotion promotionname){
         Promotion promocao = promotionService.updatePromotionName(idpromotion, promotionname.getPromotionName());
         return ResponseEntity.ok(promocao);
     }
     
     //método para alterar a descrição de uma promoção
     @PatchMapping("/{idpromotion}/updatepromotiondescription")
     public ResponseEntity<Promotion> updatePromotionDescription(@PathVariable Long idpromotion, @RequestBody Promotion promotiondescription){
         Promotion promocao = promotionService.updatePromotionDescription(idpromotion, promotiondescription.getPromotionDescription());
         return ResponseEntity.ok(promocao);
     }
     
     //método para alterar o desconto de uma promoção
     @PatchMapping("/{idpromotion}/updatepromotiondiscount")
     public ResponseEntity<Promotion> updateProductPrice(@PathVariable Long idpromotion, @RequestBody Promotion promotiondiscount){
         Promotion promocao = promotionService.updatePromotionDiscount(idpromotion, promotiondiscount.getPromotiontDiscount());
         return ResponseEntity.ok(promocao);
     }
 
     //método para listar todos os produtos cadastrados
     @GetMapping
     public ResponseEntity<List<Promotion>> getAll(){
         List<Promotion> promocoes = promotionService.searchAll();
         return ResponseEntity.ok(promocoes);
     }
     //método para deletar uma promoção
     @DeleteMapping("/{idpromotion}")
    public ResponseEntity<String> deletePromotion(@PathVariable Long idpromotion){
    promotionService.deletePromotion(idpromotion);
    return ResponseEntity.status(HttpStatus.OK).body("A Promoção foi deletada com sucesso.");
}


}
