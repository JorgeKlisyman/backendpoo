package br.unicap.si.poo.project.demo.services;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import br.unicap.si.poo.project.demo.repositories.PartnerStoreRepository;
import br.unicap.si.poo.project.demo.repositories.ProductRepository;
import br.unicap.si.poo.project.demo.repositories.RatingRepository;

import java.util.List;

import lombok.RequiredArgsConstructor;
import br.unicap.si.poo.project.demo.models.PartnerStore;

@RequiredArgsConstructor
@Service
public class PartnerStoreService {

    private final PartnerStoreRepository partnerStoreRepository;
    private final ProductRepository productRepository;
    private final RatingRepository ratingRepository;

    @Transactional
    public PartnerStore save(PartnerStore partnerStore) {
        return partnerStoreRepository.save(partnerStore);
    }

    @Transactional(readOnly = true)
    public PartnerStore searchById(Long id) {
        return partnerStoreRepository.findById(id).orElseThrow(
            () -> new RuntimeException("Essa loja não é nossa parceira. Você pode tornar-se parceiro conosco.")
        );
    }

    @Transactional(readOnly = true)
    public List<PartnerStore> searchAll() {
        return partnerStoreRepository.findAll();
    }

    @Transactional
    public PartnerStore updateNamePartnerStore(Long partnerStoreId, String partnerStoreName) {
        PartnerStore lojaParceira = searchById(partnerStoreId);
        lojaParceira.setPartnerStoreName(partnerStoreName);
        return partnerStoreRepository.save(lojaParceira);
    }

    @Transactional
    public PartnerStore updateURLPartnerStore(Long partnerStoreId, String partnerStoreURL) {
        PartnerStore lojaParceira = searchById(partnerStoreId);
        lojaParceira.setPartnerStoreUrl(partnerStoreURL);
        return partnerStoreRepository.save(lojaParceira);
    }

    @Transactional
    public void deletePartnerStore(Long partnerStoreId) {
        PartnerStore lojaParceira = searchById(partnerStoreId);
        ratingRepository.deleteByPartnerId(lojaParceira);
        productRepository.deleteBySaleBy(lojaParceira);
        partnerStoreRepository.deleteById(partnerStoreId);
    }
}
