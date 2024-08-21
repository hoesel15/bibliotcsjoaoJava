package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entity.Editora;
import com.app.repository.EditoraRepository;

@Service
public class EditoraService {

    @Autowired
    private EditoraRepository editoraRepository;

    public String save(Editora editora) {
        editoraRepository.save(editora);
        return "Salvo";
    }

    public void saveAll(List<Editora> editoras) {
        editoraRepository.saveAll(editoras);
    }

    public List<Editora> findAll() {
        return editoraRepository.findAll();
    }

    public Editora findById(long id) {
        return editoraRepository.findById(id).orElse(null);
    }

    public String update(Editora editora, long id) {
        if (editoraRepository.existsById(id)) {
            editora.setId(id);
            editoraRepository.save(editora);
            return "Atualizado";
        } else {
            return "Não encontrado";
        }
    }

    public String delete(long id) {
        if (editoraRepository.existsById(id)) {
            editoraRepository.deleteById(id);
            return "Deletado";
        } else {
            return "Não foi possivel encontrar a editora";
        }
    }

    public void deleteAll() {
        editoraRepository.deleteAll();
    }

    public List<Editora> findByNome(String nome) {
        return editoraRepository.findByNome(nome);
    }
}
