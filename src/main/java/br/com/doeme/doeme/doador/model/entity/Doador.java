package br.com.doeme.doeme.doador.model.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "tb_doador")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Doador implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O nome não pode estar vazio")
    @Size(min = 3, max = 60)
    //@Min(3)
    //@Max(60)
    @Pattern(regexp = "[a-zA-Z0-9]+", message = "must not contain special characters")
    private String name;

    @NotNull(message = "O e-mail não pode estar vazio")
    @Email
    private String email;

    public void update(Long id, Doador pessoa) {
        this.id = id;
        this.name = pessoa.getName();
        this.email = pessoa.getEmail();
    }
}
