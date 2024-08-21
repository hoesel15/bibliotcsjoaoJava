package com.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
public class Editora {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 3, message = "O nome deve obter mais de 3 caracteres.")
    private String nome;

    @NotNull(message = "Telefone não pode ser nulo.")
    @Pattern(regexp = "\\(\\d{2}\\) \\d{4,5}-\\d{4}", message = "Numero de telefone deve conter o formato (XX) XXXX-XXXX ou (XX) XXXXX-XXXX.")
    private String telefone;

    @Pattern(regexp = "\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}", message = "CNPJ deve conter formato conter XX.XXX.XXX/XXXX-XX")
    @NotBlank(message = "CNPJ não pode ser nulo.")
    private  String cnpj;

    @OneToMany(mappedBy = "editora", cascade = CascadeType.PERSIST)
    @JsonIgnoreProperties("editora")
    private List<Item> itens;
}
