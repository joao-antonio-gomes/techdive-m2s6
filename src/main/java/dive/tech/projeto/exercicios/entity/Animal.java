package dive.tech.projeto.exercicios.entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class Animal implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "O id do animal não pode ser nulo.")
    private Long id;

    @Size(min=3, message = "O nome deve ter no mínimo 3 caracteres!")
    @Size(max=20, message = "O nome precisa ter no máximo 20 caracteres!")
    @NotNull(message = "O nome do animal não pode ser nulo.")
    private String nome;
    @Size(min=5, message = "A especie precisa ter no mínimo 5 caracteres!")
    @NotNull(message = "A especie do animal não pode ser nulo.")
    private String especie;

    public Long getId() {
        return id;
    }

    public Animal() {
    }

    public Animal(Long id, String nome, String especie) {
        this.id = id;
        this.nome = nome;
        this.especie = especie;
    }

    public Animal(String nome, String especie) {
        this.nome = nome;
        this.especie = especie;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }
}
