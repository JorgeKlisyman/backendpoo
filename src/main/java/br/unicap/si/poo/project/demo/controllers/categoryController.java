package br.unicap.si.poo.project.demo.controllers;

import br.unicap.si.poo.project.demo.models.Category;
import br.unicap.si.poo.project.demo.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/categories") // caminho para acessar o controller e as suas funções relacionadas a entidade
                                     // categoria
@CrossOrigin(origins = "*")
public class categoryController {

    private final CategoryService categoryService;

    // método para adicionar uma nova categoria ao sistema do banco de dados
    @PostMapping
    public ResponseEntity<Category> create(@RequestBody Category category) {
        Category categoria = categoryService.save(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoria);
    }

    // método para buscar uma nova categoria ao sistema do banco de dados pelo id
    @GetMapping("/{categoryId}") // o id vai ser enviado como um parâmetro de caminho/path
    public ResponseEntity<Category> getById(@PathVariable Long categoryId) {
        Category categoria = categoryService.searchById(categoryId);
        return ResponseEntity.ok(categoria);
    }

    // método para alterar o nome de alguma categoria
    @PatchMapping("/{categoryId}/updatecategoryname")
    public ResponseEntity<Category> updateNameCategory(@PathVariable Long categoryId, @RequestBody Category category) {
        Category categoria = categoryService.updateNameCategory(categoryId, category.getCategoryName());
        return ResponseEntity.ok(categoria);
    }

    // método para listar todas as categorias cadastradas
    @GetMapping
    public ResponseEntity<List<Category>> getAll() {
        List<Category> categorias = categoryService.searchAll();
        return ResponseEntity.ok(categorias);
    }

    // método para deletar uma categoria
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.status(HttpStatus.OK).body("A categoria escolhida foi removida com êxito.");
    }

}
