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
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome não pode ser nulo.")
    @Size(min = 3, message = "Nome deve conter no minímo 3 caracteres.")
    private String nome;

    @NotBlank(message = "Login não pode ser nulo.")
    @Size(min = 5, message = "login deve conter no mínimo 5 caracteres.")
    private String login;

    @NotBlank(message = "Senha não pode ser nula.")
    @Size(min = 8, message = "Senha deve conter no mínimo 8 caracteres.")
    private String senha;

    @NotNull(message = "Idade não pode ser nula.")
    @Min(value = 0, message = "Idade não pode ser negativa.")
    private Integer idade;

    @NotNull(message = "CPF não pode ser nulo.")
    @CPF(message = "CPF deve conter fomato 123.456.789.01")
    private String cpf;

    @NotNull(message = "CEP não pode ser nulo.")
    @Pattern(regexp = "\\d{5}-\\d{3}", message = "CEP deve conter o formato 12345-678.")
    private String cep;

    @NotNull(message = "Telefone não pode ser nulo.")
    @Pattern(regexp = "\\(\\d{2}\\) \\d{4,5}-\\d{4}", message = "Número de telefone deve conter o formato (XX) XXXX-XXXX ou (XX) XXXXX-XXXX.")
    private String telefone;

    @Email(message = "E-mail deve conter fomato exemplo@exemplo.com.")
    private String email;

    @OneToMany(mappedBy = "funcionario", cascade = CascadeType.PERSIST)
    @JsonIgnoreProperties("funcionario")
    private List<Venda> vendas;
}

