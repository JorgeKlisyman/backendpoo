package br.unicap.si.poo.project.demo.repositories;
import br.unicap.si.poo.project.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> /*todos os métodos do crud(get, delete, etc...) serão feitos com o parâmetro do id dos usuarios*/ {
    //exemplo: deletar usuario por id, buscar por id, etc...
    //o extends jpa faz com o que o repositorio de usuario implemente todas as funcionalidades do jpa, que são os métodos do crud(delete, get, etc)
}
