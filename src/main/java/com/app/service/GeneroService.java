package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entity.Genero;
import com.app.repository.GeneroRepository;

@Service
public class GeneroService {

    @Autowired
    private GeneroRepository generoRepository;

    public String save(Genero genero) {
        generoRepository.save(genero);
        return "Salvo";
    }

    public void saveAll(List<Genero> generos) {
        generoRepository.saveAll(generos);
    }

    public List<Genero> findAll() {
        return generoRepository.findAll();
    }

    public Genero findById(long id) {
        return generoRepository.findById(id).orElse(null);
    }

    public String update(Genero genero, long id) {
        if (generoRepository.existsById(id)) {
            genero.setId(id);
            generoRepository.save(genero);
            return "Atualizado";
        } else {
            return "Não foi possivel encontrar o genero";
        }
    }

    public String delete(long id) {
        if (generoRepository.existsById(id)) {
            generoRepository.deleteById(id);
            return "Deletado";
        } else {
            return "Não foi impossivel encontrar o genero";
        }
    }

    public void deleteAll() {
        generoRepository.deleteAll();
    }

    public List<Genero> findByNome(String nome) {
        return generoRepository.findByNome(nome);
    }
}