package com.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Venda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "cliente_id")
    @NotNull(message = "cliente nao pode ser nulo")
    @JsonIgnoreProperties("vendas")
    private Cliente cliente;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "funcionario_id")
    @JsonIgnoreProperties("vendas")
    private Funcionario funcionario;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JsonIgnoreProperties("vendas")
    @JoinTable(
            name = "venda_item",
            joinColumns = @JoinColumn(name = "venda_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    @NotEmpty(message = "Tem que selecionar pelo menos 1 item")
    private List<Item> itens;

    @Positive(message = "valor total não pode dar negativo")
    private Double total_valor;

    @NotBlank(message = "Observaçoes nao pode ser vazio")
    private String observacao;
}
