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

import com.app.entity.Cliente;
import com.app.service.ClienteService;

@RestController
@RequestMapping("api/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Cliente cliente) {
        try {
            String mensagem = this.clienteService.save(cliente);
            return new ResponseEntity<>(mensagem, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>("Erro", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/saveAll")
    public ResponseEntity<String> saveAll(@RequestBody List<Cliente> clientes) {
        try {
            this.clienteService.saveAll(clientes);
            return new ResponseEntity<>("Clientes Salvos", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Cliente>> findAll() {
        try {
            List<Cliente> clientes = clienteService.findAll();
            return new ResponseEntity<>(clientes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@RequestBody Cliente cliente, @PathVariable long id){
        try {
            String mensagem = this.clienteService.update(cliente, id);
            return new ResponseEntity<>(mensagem, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro", HttpStatus.BAD_REQUEST );
        }
    }

    @GetMapping("/findById/{idCliente}")
    public ResponseEntity<Cliente> findById(@PathVariable long idCliente) {
        try {
            Cliente cliente = this.clienteService.findById(idCliente);
            if (cliente != null) {
                return new ResponseEntity<>(cliente, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{idCliente}")
    public ResponseEntity<String> delete(@PathVariable long idCliente) {
        try {
            String mensagem = this.clienteService.delete(idCliente);
            return new ResponseEntity<>(mensagem, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<String> deleteAll() {
        try {
            this.clienteService.deleteAll();
            return new ResponseEntity<>("Clientes deletados", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByNome")
    public ResponseEntity<List<Cliente>> findByNome(@RequestParam String nome) {
        try {
            List<Cliente> lista = this.clienteService.findByNome(nome);
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
