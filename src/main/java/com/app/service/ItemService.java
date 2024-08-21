package com.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entity.Autor;
import com.app.entity.Editora;
import com.app.entity.Genero;
import com.app.entity.Item;
import com.app.repository.AutorRepository;
import com.app.repository.EditoraRepository;
import com.app.repository.GeneroRepository;
import com.app.repository.ItemRepository;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private EditoraRepository editoraRepository;

    @Autowired
    private GeneroRepository generoRepository;

    @Autowired
    private AutorRepository autorRepository;

    public String save(Item item) {
        if (item.getEditora() != null && item.getEditora().getId() != null) {
            Editora editora = editoraRepository.findById(item.getEditora().getId())
                    .orElseThrow(() -> new RuntimeException("Não foi possivel encontrar a editora"));
            item.setEditora(editora);
        }

        if (item.getGenero() != null && item.getGenero().getId() != null) {
            Genero genero = generoRepository.findById(item.getGenero().getId())
                    .orElseThrow(() -> new RuntimeException("Não foi possivel encontrar o genero"));
            item.setGenero(genero);
        }

        if (item.getAutores() != null) {
            List<Autor> autores = item.getAutores().stream()
                    .map(autor -> autorRepository.findById(autor.getId())
                            .orElseThrow(() -> new RuntimeException("Não foi possivel encontrar o autor")))
                    .collect(Collectors.toList());
            item.setAutores(autores);
        }

        itemRepository.save(item);
        return "Item salvo com sucesso!";
    }

    public void saveAll(List<Item> items) {
        for (Item item : items) {
            if (item.getEditora() != null && item.getEditora().getId() != null) {
                Editora editora = editoraRepository.findById(item.getEditora().getId())
                        .orElseThrow(() -> new RuntimeException("Não foi possivel encontrar a editora"));
                item.setEditora(editora);
            }

            if (item.getGenero() != null && item.getGenero().getId() != null) {
                Genero genero = generoRepository.findById(item.getGenero().getId())
                        .orElseThrow(() -> new RuntimeException("Não foi possivel encontrar o genero"));
                item.setGenero(genero);
            }

            if (item.getAutores() != null && !item.getAutores().isEmpty()) {
                List<Autor> autores = item.getAutores().stream()
                        .map(autor -> autorRepository.findById(autor.getId())
                                .orElseThrow(() -> new RuntimeException("Não foi possivel encontrar o autor")))
                        .collect(Collectors.toList());
                item.setAutores(autores);
            }
        }
        itemRepository.saveAll(items);
    }

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    public Item findById(long id) {
        return itemRepository.findById(id).orElse(null);
    }

    public String update(Item item, long id) {
        if (itemRepository.existsById(id)) {
            item.setId(id);
            itemRepository.save(item);
            return "Atualizado";
        } else {
            return "Não foi possivel encontrar o item";
        }
    }

    public String delete(long id) {
        if (itemRepository.existsById(id)) {
            itemRepository.deleteById(id);
            return "Deletado!";
        } else {
            return "Não foi possivel encontrar o item!";
        }
    }

    public void deleteAll() {
        itemRepository.deleteAll();
    }

    public List<Item> findByTitulo(String titulo) {
        return itemRepository.findByTitulo(titulo);
    }
}