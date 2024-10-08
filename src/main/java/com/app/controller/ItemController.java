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

import com.app.entity.Item;
import com.app.service.ItemService;

@RestController
@RequestMapping("api/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Item item) {
        try {
            String mensagem = this.itemService.save(item);
            return new ResponseEntity<>(mensagem, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>("Erro", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/saveAll")
    public ResponseEntity<String> saveAll(@RequestBody List<Item> items) {
        try {
            this.itemService.saveAll(items);
            return new ResponseEntity<>("Itens salvos", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Item>> findAll() {
        try {
            List<Item> items = itemService.findAll();
            return new ResponseEntity<>(items, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@RequestBody Item item, @PathVariable long id){
        try {
            String mensagem = this.itemService.update(item, id);
            return new ResponseEntity<>(mensagem, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro", HttpStatus.BAD_REQUEST );
        }
    }

    @GetMapping("/findById/{idItem}")
    public ResponseEntity<Item> findById(@PathVariable long idItem) {
        try {
            Item item = this.itemService.findById(idItem);
            if (item != null) {
                return new ResponseEntity<>(item, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{idItem}")
    public ResponseEntity<String> delete(@PathVariable long idItem) {
        try {
            String mensagem = this.itemService.delete(idItem);
            return new ResponseEntity<>(mensagem, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<String> deleteAll() {
        try {
            this.itemService.deleteAll();
            return new ResponseEntity<>("Itens deletados", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByTitulo")
    public ResponseEntity<List<Item>> findByTitulo(@RequestParam String titulo) {
        try {
            List<Item> lista = this.itemService.findByTitulo(titulo);
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}