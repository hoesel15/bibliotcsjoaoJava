package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.entity.Genero;
import com.app.service.GeneroService;

@RestController
@RequestMapping("api/genero")
public class GeneroController {

    @Autowired
    private GeneroService generoService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Genero genero) {
        try {
            String mensagem = this.generoService.save(genero);
            return new ResponseEntity<>(mensagem, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>("Erro" , HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/saveAll")
    public ResponseEntity<String> saveAll(@RequestBody List<Genero> generos) {
        try {
            this.generoService.saveAll(generos);
    return new ResponseEntity<>("Generos salvos", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Genero>> findAll() {
        try {
            List<Genero> generos = generoService.findAll();
            return new ResponseEntity<>(generos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

     @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@RequestBody Genero genero, @PathVariable long id){
        try {
            String mensagem = this.generoService.update(genero, id);
            return new ResponseEntity<>(mensagem, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro", HttpStatus.BAD_REQUEST );
        }
    }

    @GetMapping("/findById/{idGenero}")
    public ResponseEntity<Genero> findById(@PathVariable long idGenero) {
        try {
            Genero genero = this.generoService.findById(idGenero);
            if (genero != null) {
                return new ResponseEntity<>(genero, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{idGenero}")
    public ResponseEntity<String> delete(@PathVariable long idGenero) {
        try {
            String mensagem = this.generoService.delete(idGenero);
            return new ResponseEntity<>(mensagem, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<String> deleteAll() {
        try {
            this.generoService.deleteAll();
            return new ResponseEntity<>("Generos alterados", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByNome")
    public ResponseEntity<List<Genero>> findByNome(@RequestParam String nome) {
        try {
            List<Genero> lista = this.generoService.findByNome(nome);
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}