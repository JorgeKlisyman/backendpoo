package br.unicap.si.poo.project.demo.controllers;

import br.unicap.si.poo.project.demo.models.PartnerStore;
import br.unicap.si.poo.project.demo.services.PartnerStoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller para gerenciar as operações relacionadas às lojas parceiras.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/partnerstores")
@CrossOrigin(origins = "*")
public class partnerStoreController {

    private final PartnerStoreService partnerStoreService;

    /**
     * Cria uma nova loja parceira.
     *
     * @param partnerStore Objeto da loja parceira a ser criada.
     * @return A loja parceira criada com o status HTTP 201.
     */
    @PostMapping
    public ResponseEntity<PartnerStore> create(@RequestBody PartnerStore partnerStore) {
        PartnerStore lojaparceira = partnerStoreService.save(partnerStore);
        return ResponseEntity.status(HttpStatus.CREATED).body(lojaparceira);
    }

    /**
     * Busca uma loja parceira pelo ID.
     *
     * @param partnerStoreId ID da loja parceira a ser buscada.
     * @return A loja parceira encontrada com o status HTTP 200.
     */
    @GetMapping("/{partnerStoreId}")
    public ResponseEntity<PartnerStore> getById(@PathVariable Long partnerStoreId) {
        PartnerStore lojaparceira = partnerStoreService.searchById(partnerStoreId);
        return ResponseEntity.ok(lojaparceira);
    }

    /**
     * Atualiza o nome de uma loja parceira.
     *
     * @param partnerStoreId ID da loja parceira a ser atualizada.
     * @param partnerStore Objeto contendo o novo nome da loja parceira.
     * @return A loja parceira atualizada com o status HTTP 200.
     */
    @PatchMapping("/{partnerStoreId}/updatenamepartnerstore")
    public ResponseEntity<PartnerStore> updateNamePartnerStore(
            @PathVariable Long partnerStoreId,
            @RequestBody PartnerStore partnerStore) {
        PartnerStore lojaparceira = partnerStoreService.updateNamePartnerStore(
                partnerStoreId,
                partnerStore.getPartnerStoreName());
        return ResponseEntity.ok(lojaparceira);
    }

    /**
     * Atualiza a URL de uma loja parceira.
     *
     * @param partnerStoreId ID da loja parceira a ser atualizada.
     * @param partnerStore Objeto contendo a nova URL da loja parceira.
     * @return A loja parceira atualizada com o status HTTP 200.
     */
    @PatchMapping("/{partnerStoreId}/updateurlpartnerstore")
    public ResponseEntity<PartnerStore> updateURLPartnerStore(
            @PathVariable Long partnerStoreId,
            @RequestBody PartnerStore partnerStore) {
        PartnerStore lojaparceira = partnerStoreService.updateURLPartnerStore(
                partnerStoreId,
                partnerStore.getPartnerStoreUrl());
        return ResponseEntity.ok(lojaparceira);
    }

    /**
     * Lista todas as lojas parceiras cadastradas.
     *
     * @return A lista de lojas parceiras com o status HTTP 200.
     */
    @GetMapping
    public ResponseEntity<List<PartnerStore>> getAll() {
        List<PartnerStore> lojasparceiras = partnerStoreService.searchAll();
        return ResponseEntity.ok(lojasparceiras);
    }

    /**
     * Deleta uma loja parceira pelo ID.
     *
     * @param partnerStoreId ID da loja parceira a ser deletada.
     * @return Mensagem de confirmação com o status HTTP 200.
     */
    @DeleteMapping("/{partnerStoreId}")
    public ResponseEntity<String> deletePartnerStore(@PathVariable Long partnerStoreId) {
        partnerStoreService.deletePartnerStore(partnerStoreId);
        return ResponseEntity.status(HttpStatus.OK).body("Partner Store foi deletada com sucesso.");
    }
}
