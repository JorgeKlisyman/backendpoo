package br.unicap.si.poo.project.demo.services;

import br.unicap.si.poo.project.demo.repositories.*;
import br.unicap.si.poo.project.demo.models.*;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;


    //método que cadastra um novo produto
    @Transactional
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Transactional(readOnly=true) //readonly true método de consulta. nada é adicionado ou removido ou alterado.
    public Product searchById(Long idproduct) {
        return productRepository.findById(idproduct).orElseThrow(() -> new RuntimeException("O produto não foi encontrado"));
    }

    //método para alterar o nome de algum produto
    @Transactional
    public Product updateProductName(Long idproduct, String nameproduct) {
        Product produto = searchById(idproduct);
        produto.setProductName(nameproduct); //alterando o nome da instância PRODUCT. os valores foram armazenados nessa variavel produto do tipo Product. ela guarda o novo valor que será atribuido ao atributo que guarda o nome do produto
        return productRepository.save(produto);
    }
    //método para alterar a descrição de produto
    @Transactional
    public Product updateProductDescription(Long idproduct, String description) {
        Product produto = searchById(idproduct);
        produto.setProductDescription(description);
        return productRepository.save(produto);
    }
        //método para alterar o preço de um produto
        @Transactional
        public Product updateProductPrice(Long idproduct, double price) {
            Product produto = searchById(idproduct);
            produto.setProductPrice(price);
            return productRepository.save(produto);
        }

    @Transactional(readOnly = true)
    public List<Product> searchAll() {
        return productRepository.findAll();
    }

          @Transactional
          public void deleteProduct(Long idproduct){
              if (!productRepository.existsById(idproduct)){
                  throw new RuntimeException ("Não foi possível deletar esse produto. ERRO!!!");
              }
              else{ productRepository.deleteById(idproduct);}
}
}
