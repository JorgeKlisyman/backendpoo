package br.unicap.si.poo.project.demo.services;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.unicap.si.poo.project.demo.models.Administrator;
import br.unicap.si.poo.project.demo.repositories.AdministratorRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AdministratorService {

    private final AdministratorRepository administratorRepository;

    @Transactional
    public Administrator save(Administrator administrator) {
        return administratorRepository.save(administrator);
    }

    @Transactional(readOnly = true)
    public Administrator searchById(Long id) {
        return administratorRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Administrador não encontrado")
        );
    }

    // alterar a senha de um administrador
    @Transactional
    public Administrator updatePassword(Long id, String passwordAdministrator) {
        Administrator administrador = searchById(id);
        administrador.setPasswordAdministrator(passwordAdministrator);
        return administratorRepository.save(administrador);
    }


    // alterar o email de um administrador
    @Transactional
    public Administrator updateEmail(Long id, String emailAdministrator) {
        Administrator administrador = searchById(id);
        administrador.setEmailAdministrator(emailAdministrator);
        return administratorRepository.save(administrador);
    }

    // alterar o nome do administrador
    @Transactional
    public Administrator updateName(Long id, String nameAdministrator) {
        Administrator administrador = searchById(id);
        administrador.setNameAdministrator(nameAdministrator);
        return administratorRepository.save(administrador);
    }

    // consultar todos os administradores existentes
    @Transactional(readOnly = true)
    public List<Administrator> searchAll() {
        return administratorRepository.findAll();
    }

    @Transactional
    public boolean deleteAdmin(Long id) {
        if (!administratorRepository.existsById(id)) {
            throw new RuntimeException("Não foi possível deletar o administrador pois ele não foi encontrado.");
        } else {
            administratorRepository.deleteById(id);
            return true; // Retorna true se o administrador foi deletado com sucesso
        }
    }
    
}
