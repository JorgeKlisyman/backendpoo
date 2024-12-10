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

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/administrators") // caminho para acessar o controller e as suas funções relacionadas à entidade
@CrossOrigin(origins = "*")
public class administratorController {

    private final AdministratorService administratorService;

    @Autowired
    private final JdbcTemplate jdbcTemplate;  // Injeção do JdbcTemplate para consulta direta ao banco

    // Método para adicionar um novo adm que será chamado no service
    @PostMapping
    public ResponseEntity<Administrator> create(@Validated @RequestBody Administrator administrator) {
        Administrator administrador = administratorService.save(administrator);
        return ResponseEntity.status(HttpStatus.CREATED).body(administrador);
    }

    // Método para buscar um adm no banco de dados pelo id
    @GetMapping("/{id}")
    public ResponseEntity<Administrator> getById(@PathVariable Long id) {
        Administrator administrador = administratorService.searchById(id);
        return administrador != null
                ? ResponseEntity.ok(administrador)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    // Método para alterar a senha de um adm
    @PatchMapping("/{id}/updateadmpassword")
    public ResponseEntity<Administrator> updatePassword(@PathVariable Long id,
                                                       @RequestBody Administrator administrator) {
        Administrator administrador = administratorService.updatePassword(id, administrator.getPasswordAdministrator());
        return administrador != null
                ? ResponseEntity.ok(administrador)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    // Método para alterar o email de um adm
    @PatchMapping("/{id}/updateadmemail")
    public ResponseEntity<Administrator> updateEmail(@PathVariable Long id, @RequestBody Administrator administrator) {
        Administrator administrador = administratorService.updateEmail(id, administrator.getEmailAdministrator());
        return administrador != null
                ? ResponseEntity.ok(administrador)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    // Método para alterar o nome de um adm
    @PatchMapping("/{id}/updateadmusername")
    public ResponseEntity<Administrator> updateName(@PathVariable Long id, @RequestBody Administrator administrator) {
        Administrator administrador = administratorService.updateName(id, administrator.getNameAdministrator());
        return administrador != null
                ? ResponseEntity.ok(administrador)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    // Método para listar todos os adms cadastrados
    @GetMapping
    public ResponseEntity<List<Administrator>> getAll() {
        List<Administrator> administradores = administratorService.searchAll();
        return ResponseEntity.ok(administradores);
    }

    // Método para deletar um adm
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAdmin(@PathVariable Long id) {
        boolean deleted = administratorService.deleteAdmin(id);
        if (deleted) {
            return ResponseEntity.status(HttpStatus.OK).body("O administrador foi deletado com sucesso.");
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
}
