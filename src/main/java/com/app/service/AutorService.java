package com.app.service;

import com.app.entity.Autor;
import com.app.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    public String save(Autor autor) {
        autorRepository.save(autor);
        return "Autor salvo";
    }

    public void saveAll(List<Autor> autores) {
        autorRepository.saveAll(autores);
    }

    public List<Autor> findAll() {
        return autorRepository.findAll();
    }

    public Autor findById(long id) {
        return autorRepository.findById(id).orElse(null);
    }

    public String update(Autor autor, long id) {
        if (autorRepository.existsById(id)) {
            autor.setId(id);
            autorRepository.save(autor);
            return "Autor atualizado";
        } else {
            return "Não foi encontrado o autor";
        }
    }

    public String delete(long id) {
        if (autorRepository.existsById(id)) {
            autorRepository.deleteById(id);
            return "Autor deletado";
        } else {
            return "Não foi encontrado o autor";
        }
    }

    public void deleteAll() {
        autorRepository.deleteAll();
    }

    public List<Autor> findByNome(String nome) {
        return autorRepository.findByNome(nome);
    }
}