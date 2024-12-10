package br.unicap.si.poo.project.demo.controllers;

import br.unicap.si.poo.project.demo.services.*;
import br.unicap.si.poo.project.demo.models.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/users") // caminho para acessar o controller e as suas funções relacionadas à entidade
                                // usuario
@CrossOrigin(origins = "*")
public class userController {

    private final UserService userService;

    // método para adicionar um novo usuario ao sistema do banco de dados
    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) {
        User usuario = userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

    // método para buscar um novo usuario ao sistema do banco de dados pelo id
    @GetMapping("/{id}") // o id vai ser enviado como um parâmetro de caminho/path
    public ResponseEntity<User> getById(@PathVariable Long id) {
        User usuario = userService.searchById(id);
        return ResponseEntity.ok(usuario);
    }

    // método para alterar a senha de um usuario
    @PatchMapping("/{id}/updatepassword")
    public ResponseEntity<User> updatePassword(@PathVariable Long id, @RequestBody User user) {
        User usuario = userService.updatePassword(id, user.getPassword());
        return ResponseEntity.ok(usuario);
    }

    // método para alterar o email de um usuario
    @PatchMapping("/{id}/updateemail")
    public ResponseEntity<User> updateEmail(@PathVariable Long id, @RequestBody User user) {
        User usuario = userService.updateEmail(id, user.getEmail());
        return ResponseEntity.ok(usuario);
    }

    // método para alterar o nome de um usuario
    @PatchMapping("/{id}/updateusername")
    public ResponseEntity<User> updateUsername(@PathVariable Long id, @RequestBody User user) {
        User usuario = userService.updateUsername(id, user.getUsername());
        return ResponseEntity.ok(usuario);
    }

    // método para listar todos os usuarios cadastrados
    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        List<User> usuarios = userService.searchAll();
        return ResponseEntity.ok(usuarios);
    }

    // método para deletar um user
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).body("O usuario foi deletado com sucesso.");
    }
}
