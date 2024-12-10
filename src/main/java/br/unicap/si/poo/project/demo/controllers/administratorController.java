package br.unicap.si.poo.project.demo.controllers;

import br.unicap.si.poo.project.demo.models.Administrator;
import br.unicap.si.poo.project.demo.services.AdministratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/administrators") // Caminho para acessar o controlador e as funções relacionadas à entidade
@CrossOrigin(origins = "*")
public class AdministratorController {

    private final AdministratorService administratorService;

    @Autowired
    private final JdbcTemplate jdbcTemplate;  // Injeção do JdbcTemplate para consulta direta ao banco

    // Método para adicionar um novo administrador
    @PostMapping
    public ResponseEntity<Administrator> create(@Validated @RequestBody Administrator administrator) {
        Administrator savedAdmin = administratorService.save(administrator);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAdmin);
    }

    // Método para buscar um administrador pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Administrator> getById(@PathVariable Long id) {
        Administrator admin = administratorService.searchById(id);
        return admin != null ? ResponseEntity.ok(admin) : ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    // Método para alterar a senha de um administrador
    @PatchMapping("/{id}/update-password")
    public ResponseEntity<Administrator> updatePassword(@PathVariable Long id, @RequestBody Administrator administrator) {
        Administrator updatedAdmin = administratorService.updatePassword(id, administrator.getPasswordAdministrator());
        return updatedAdmin != null ? ResponseEntity.ok(updatedAdmin) : ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    // Método para alterar o email de um administrador
    @PatchMapping("/{id}/update-email")
    public ResponseEntity<Administrator> updateEmail(@PathVariable Long id, @RequestBody Administrator administrator) {
        Administrator updatedAdmin = administratorService.updateEmail(id, administrator.getEmailAdministrator());
        return updatedAdmin != null ? ResponseEntity.ok(updatedAdmin) : ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    // Método para alterar o nome de um administrador
    @PatchMapping("/{id}/update-name")
    public ResponseEntity<Administrator> updateName(@PathVariable Long id, @RequestBody Administrator administrator) {
        Administrator updatedAdmin = administratorService.updateName(id, administrator.getNameAdministrator());
        return updatedAdmin != null ? ResponseEntity.ok(updatedAdmin) : ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    // Método para listar todos os administradores
    @GetMapping
    public ResponseEntity<List<Administrator>> getAll() {
        List<Administrator> administrators = administratorService.searchAll();
        return ResponseEntity.ok(administrators);
    }

    // Método para deletar um administrador
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAdmin(@PathVariable Long id) {
        boolean deleted = administratorService.deleteAdmin(id);
        if (deleted) {
            return ResponseEntity.ok("O administrador foi deletado com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Administrador não encontrado.");
        }
    }

    // Método para testar a conexão com o banco de dados
    @GetMapping("/test-db")
    public ResponseEntity<String> testDatabaseConnection() {
        try {
            // Tentativa de execução de uma consulta simples
            jdbcTemplate.execute("SELECT 1");
            return ResponseEntity.ok("Conexão com o banco de dados bem-sucedida!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Erro ao conectar com o banco de dados: " + e.getMessage());
        }
    }

    // Endpoint simples para verificar se o servidor está ativo
    @GetMapping("/")
    public ResponseEntity<String> home() {
        return ResponseEntity.ok("Bem-vindo ao servidor!");
    }

    // Endpoint que retorna dados exemplo
    @GetMapping("/data")
    public ResponseEntity<Map<String, String>> getData() {
        Map<String, String> data = new HashMap<>();
        data.put("message", "Exemplo de dados retornados");
        return ResponseEntity.ok(data);
    }
}
