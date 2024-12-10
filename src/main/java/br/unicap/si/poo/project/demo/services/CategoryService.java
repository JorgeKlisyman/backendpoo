package br.unicap.si.poo.project.demo.services;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.unicap.si.poo.project.demo.repositories.CategoryRepository;
import br.unicap.si.poo.project.demo.repositories.ProductRepository;

import java.util.List;

import lombok.RequiredArgsConstructor;

import br.unicap.si.poo.project.demo.models.Category;

@RequiredArgsConstructor // essa anotação é do lombok e serve para que o lombok crie um método construtor
                         // com a variável "categoryRepository", já que os métodos são desenvolvidos
                         // aqui. dessa forma, a injeção de dependência é feita via método construtor
@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;

    // método pra criar uma nova categoria
    @Transactional
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    // método para consultar se alguma categoria existe
    @Transactional(readOnly = true) // deixando claro que esse é um método apenas de consulta
    public Category searchById(Long id) {
        return categoryRepository.findById(id).orElseThrow(

                () -> new RuntimeException("Essa categoria ainda não existe."));
    }

    // método para listar todas as categorias que existem
    @Transactional(readOnly = true)
    public List<Category> searchAll() {
        return categoryRepository.findAll();
    }

    // método para alterar o nome de alguma categoria
    public Category updateNameCategory(Long categoryId, String categoryName) {
        Category categoria = searchById(categoryId);
        categoria.setCategoryName(categoryName);
        return categoryRepository.save(categoria);
    }

    @Transactional
    public void deleteCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElse(null);
        if (category == null) {
            throw new RuntimeException("Não foi possível deletar essa categoria. ERRO!!!");
        } else {
            productRepository.deleteByProductCategory(category);
            categoryRepository.deleteById(categoryId);
        }
    }

}
