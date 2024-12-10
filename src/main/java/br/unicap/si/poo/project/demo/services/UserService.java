package br.unicap.si.poo.project.demo.services;

import br.unicap.si.poo.project.demo.repositories.*;
import br.unicap.si.poo.project.demo.models.*;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor //essa anotação é do lombok e serve para que o lombok crie um método construtor com a variável "UserRepository", já que os métodos são desenvolvidos aqui. dessa forma, a injeção de dependência é feita via método construtor
@Service
public class UserService {

    private final RatingRepository ratingRepository;
    private final UserRepository userRepository;

    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    @Transactional(readOnly = true) //deixando claro que esse é um método apenas de consulta
    public User searchById(Long id) {
        return userRepository.findById(id).orElseThrow( //busque o usuario ou lance a exceção caso o user não seja encontrado
                () -> new RuntimeException("Usuário não encontrado")
        );
    }

    @Transactional
    public User updatePassword(Long id, String password) {
        User usuario = searchById(id);
        usuario.setPassword(password); //alterando o password da instância. os valores foram armazenados nessa variavel usuario do tipo User, indicando que ela guarda o valor da instância
        return userRepository.save(usuario);
    }

    //método para alterar email do usuario
    @Transactional
    public User updateEmail(Long id, String email) {
        User usuario = searchById(id);
        usuario.setEmail(email); //alterando o email da instância. os valores foram armazenados nessa variavel usuario do tipo User, indicando que ela guarda o valor da instância
        return userRepository.save(usuario);
    }

    //método para alterar o nome do usuario
    @Transactional
    public User updateUsername(Long id, String username) {
        User usuario = searchById(id);
        usuario.setUsername(username); //alterando o username da instância. os valores foram armazenados nessa variavel usuario do tipo User, indicando que ela guarda o valor da instância
        return userRepository.save(usuario);
    }

    @Transactional(readOnly = true)
    public List<User> searchAll() {
        return userRepository.findAll();
    }

    //deletar um usuário
    @Transactional
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new RuntimeException("Não foi possível deletar esse usuário. ERRO!!!");
        } else {
            ratingRepository.deleteByUserId(user);
            userRepository.deleteById(id);
        }
    }
}
