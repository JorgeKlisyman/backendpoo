package br.unicap.si.poo.project.demo.controllers;

import br.unicap.si.poo.project.demo.models.Category;
import br.unicap.si.poo.project.demo.models.PartnerStore;
import br.unicap.si.poo.project.demo.models.Product;
import br.unicap.si.poo.project.demo.services.ProductService;
import br.unicap.si.poo.project.demo.dtos.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Base64;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/products")
@CrossOrigin(origins = "*")

public class productController {

    private final ProductService productService;

    // método para adicionar um novo produto ao sistema do banco de dados
    @PostMapping
    public ResponseEntity<Product> create(@RequestBody ProductDto productDTO) {
        Product product = new Product();
        product.setProductName(productDTO.getProductName());
        product.setProductDescription(productDTO.getProductDescription());
        product.setProductPrice(productDTO.getProductPrice());

        Category productCategory = new Category();
        productCategory.setCategoryId(productDTO.getProductCategory());
        product.setProductCategory(productCategory);
        // Recupera a instância de PartnerStore com base no ID fornecido
        PartnerStore saleBy = new PartnerStore();
        saleBy.setPartnerStoreId(productDTO.getSaleBy());
        product.setSaleBy(saleBy);

        if (productDTO.getProductImage() != null) {
            byte[] imageBytes = Base64.getDecoder().decode(productDTO.getProductImage());
            product.setProductImage(imageBytes);
        }

        Product savedProduct = productService.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    // método para buscar um novo produto que esteja cadastrado ao sistema do banco
    // de dados pelo id
    @GetMapping("/{idproduct}")
    public ResponseEntity<Product> getById(@PathVariable Long idproduct) {
        Product produto = productService.searchById(idproduct);
        return ResponseEntity.ok(produto);
    }

    // método para alterar o nome de um produto
    @PatchMapping("/{idproduct}/updateproductname")
    public ResponseEntity<Product> updateProductName(@PathVariable Long idproduct, @RequestBody Product productname) {
        Product produto = productService.updateProductName(idproduct, productname.getProductName());
        return ResponseEntity.ok(produto);
    }

    // método para alterar a descrição de um produto
    @PatchMapping("/{idproduct}/updateproductdescription")
    public ResponseEntity<Product> updateProductDescription(@PathVariable Long idproduct,
            @RequestBody Product productdescription) {
        Product produto = productService.updateProductDescription(idproduct,
                productdescription.getProductDescription());
        return ResponseEntity.ok(produto);
    }

    // método para alterar o preço de um produto
    @PatchMapping("/{idproduct}/updateproductprice")
    public ResponseEntity<Product> updateProductPrice(@PathVariable Long idproduct, @RequestBody Product productprice) {
        Product produto = productService.updateProductPrice(idproduct, productprice.getProductPrice());
        return ResponseEntity.ok(produto);
    }

    // método para deletar um produto
    @DeleteMapping("/{idproduct}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long idproduct) {
        productService.deleteProduct(idproduct);
        return ResponseEntity.status(HttpStatus.OK).body("Produto selecionado foi deletada com sucesso.");
    }

    // método para listar todos os produtos cadastrados
    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        List<Product> produtos = productService.searchAll();
        return ResponseEntity.ok(produtos);
    }

}
