package com.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O tipo não pode ser nulo")
    @Size(min = 3, max = 50, message = "Tipo deve conter entre 3 e 50 caracteres.")
    private String tipo;

    @NotBlank(message = "O título não pode ser nulo.")
    @Size(min = 3, max = 100, message = "Titulo deve conter entre 3 e 100 caracteres.")
    private String titulo;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "genero_id")
    @JsonIgnoreProperties("itens")
    private Genero genero;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "item_autor",
            joinColumns = @JoinColumn(name = "item_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    @JsonIgnoreProperties("itens")
    private List<Autor> autores;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "editora_id")
    @JsonIgnoreProperties("itens")
    private Editora editora;

    @NotBlank(message = "Ano de publicação não pode ser nulo")
    @Size(min = 3, max = 4, message = "Ano de publicação deve conter 4 dígitos")
    private String anoPublicacao;

    @NotNull(message = "Ano de publicação não pode ser nulo")
    @Positive(message = "O preço do item não pode ser negativo")
    private Double preco;

    @ManyToMany(mappedBy = "itens", cascade = CascadeType.PERSIST)
    @JsonIgnoreProperties("itens")
    private List<Venda> vendas;
}
