package br.unicap.si.poo.project.demo.controllers;

import br.unicap.si.poo.project.demo.services.RatingService;
import br.unicap.si.poo.project.demo.models.Rating;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/ratings")
@CrossOrigin(origins = "*")

public class ratingController {
    private final RatingService ratingService;

    // deletar uma avaliação
    @DeleteMapping("/{idrating}")
    public ResponseEntity<String> deleteRating(@PathVariable Long idrating) {
        ratingService.deleteRating(idrating);
        return ResponseEntity.status(HttpStatus.OK).body("A avaliação foi deletada com sucesso.");
    }

    // editar o comentário da avaliação
    @PatchMapping("/{idrating}/updaterating")
    public ResponseEntity<Rating> updateComment(@PathVariable Long idrating, @RequestBody Rating rating) {
        Rating avaliacao = ratingService.updateComment(idrating, rating.getRatingComment());
        return ResponseEntity.ok(avaliacao);
    }

    // buscar todas as avaliações cadastradas
    @GetMapping
    public ResponseEntity<List<Rating>> getAll() {
        List<Rating> avaliacoes = ratingService.searchAll();
        return ResponseEntity.ok(avaliacoes);
    }

    // consultar uma avaliação pelo id
    @GetMapping("/{idrating}")
    public ResponseEntity<Rating> getById(@PathVariable Long idrating) {
        Rating avaliacao = ratingService.searchById(idrating);
        return ResponseEntity.ok(avaliacao);
    }

    // método que cria uma avaliação
    @PostMapping
    public ResponseEntity<Rating> create(@RequestBody Rating rating) {
        Rating avaliacao = ratingService.save(rating);
        return ResponseEntity.status(HttpStatus.CREATED).body(avaliacao);
    }
}
