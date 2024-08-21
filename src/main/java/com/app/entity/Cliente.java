package com.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome não deve ser nulo.")
    @Size(min = 3, message = "O nome deve obter no minímo 2 caracteres.")
    private String nome;

    @NotNull(message = "Não pode ser nulo.")
    @Min(value = 0, message = "A idade não deve ser negativa.")
    private Integer idade;

    @NotNull(message = "Não pode ser nulo.")
    @CPF(message = "CPF Inválida. O fomato deve ser 123.456.789.01")
    private String cpf;

    @NotNull(message = "CEP não pode ser nulo.")
    @Pattern(regexp = "\\d{5}-\\d{3}", message = "CEP Inválido. O formato deve ser 12345-678.")
    private String cep;

    @NotNull(message = "Telefone não pode ser nulo.")
    @Pattern(regexp = "\\(\\d{2}\\) \\d{4,5}-\\d{4}", message = "Invalido, formato deve ser (XX) XXXX-XXXX ou (XX) XXXXX-XXXX.")
    private String telefone;

    @Email(message = "Invalido, fomato deve ser exemplo@exemplo.com.")
    private String email;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.PERSIST)
    @JsonIgnoreProperties("cliente")
    private List<Venda> vendas;

}
