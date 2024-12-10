package br.unicap.si.poo.project.demo.services;

import br.unicap.si.poo.project.demo.repositories.RatingRepository;
import br.unicap.si.poo.project.demo.models.Rating;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RatingService {
    private final RatingRepository ratingRepository;

    // método que cria uma avaliação
    @Transactional
    public Rating save(Rating rating) {
        return ratingRepository.save(rating);
    }

    // consultar uma avaliação pelo id
    @Transactional(readOnly = true) // deixando claro que esse é um método apenas de consulta
    public Rating searchById(Long ratingId) {
        return ratingRepository.findById(ratingId).orElseThrow(
                () -> new RuntimeException("Avaliação não encontrada")
        );
    }

    // buscar todas as avaliações cadastradas
    @Transactional(readOnly = true)
    public List<Rating> searchAll() {
        return ratingRepository.findAll();
    }

    // editar o comentário da avaliação
    @Transactional
    public Rating updateComment(Long ratingId, String ratingComment) {
        Rating avaliacao = searchById(ratingId);
        avaliacao.setRatingComment(ratingComment);
        return ratingRepository.save(avaliacao);
    }

    // deletar uma avaliação
    @Transactional
    public String deleteRating(Long ratingId) {
        if (!ratingRepository.existsById(ratingId)) {
            throw new RuntimeException("Não foi possível deletar essa avaliação. ERRO!!!");
        } else {
            ratingRepository.deleteById(ratingId);
            return "A avaliação deletada com sucesso.";
        }
    }
}

