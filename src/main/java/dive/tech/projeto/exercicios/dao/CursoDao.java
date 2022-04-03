package dive.tech.projeto.exercicios.dao;

import dive.tech.projeto.exercicios.entity.Curso;
import dive.tech.projeto.exercicios.entity.Disciplina;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class CursoDao {

    /**
     * Essa lista retornará três cursos: "Curso 1", "Curso 2" e "Curso 3".
     * Cada curso terá três disciplinas, e cada disciplina terá o nome "Disciplina X do Curso Y".
     * Os cursos estão no seguinte formado:
     * [
     *     {
     *         "id": 1,
     *         "nome": "Curso 1",
     *         "disciplinas": [
     *             {
     *                 "id": 1,
     *                 "nome": "Disciplina 1 do Curso 1"
     *             },
     *             {
     *                 "id": 2,
     *                 "nome": "Disciplina 2 do Curso 1"
     *             },
     *             {
     *                 "id": 3,
     *                 "nome": "Disciplina 3 do Curso 1"
     *             }
     *         ]
     *     },
     *     {
     *         "id": 2,
     *         "nome": "Curso 2",
     *         "disciplinas": [
     *             {
     *                 "id": 1,
     *                 "nome": "Disciplina 1 do Curso 2"
     *             },
     *             {
     *                 "id": 2,
     *                 "nome": "Disciplina 2 do Curso 2"
     *             },
     *             {
     *                 "id": 3,
     *                 "nome": "Disciplina 3 do Curso 2"
     *             }
     *         ]
     *     },
     *     {
     *         "id": 3,
     *         "nome": "Curso 3",
     *         "disciplinas": [
     *             {
     *                 "id": 1,
     *                 "nome": "Disciplina 1 do Curso 3"
     *             },
     *             {
     *                 "id": 2,
     *                 "nome": "Disciplina 2 do Curso 3"
     *             },
     *             {
     *                 "id": 3,
     *                 "nome": "Disciplina 3 do Curso 3"
     *             }
     *         ]
     *     }
     * ]
     */
    public List<Curso> obterCursos() {
        List<Curso> cursos = new ArrayList<>();

        for (int i = 1; i < 4; i++) {
            Curso curso = new Curso(i);

            for (int j = 1; j < 4; j++) {
                Disciplina disciplina = new Disciplina(curso.getNome(), j);
                curso.getDisciplinas().add(disciplina);
            }

            cursos.add(curso);
        }
        return cursos;
    }

    public Curso salvarCurso(Curso curso) {
        Curso cursoCriado = new Curso(Math.toIntExact(curso.getId()));
        for (int j = 1; j < 4; j++) {
            Disciplina disciplina = new Disciplina(cursoCriado.getNome(), j);
            cursoCriado.getDisciplinas().add(disciplina);
        }
        return cursoCriado;
    }

    public Curso listById(int id) {
        List<Curso> cursos = this.obterCursos();
        Curso curso = cursos.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
        if (curso == null) {
            throw new NoSuchElementException("Curso não encontrado");
        }
        return curso;
    }

    public Curso update(Curso curso) {
        List<Curso> cursos = this.obterCursos();
        Curso cursoAtualizado = cursos.stream().filter(c -> c.getId() == curso.getId()).findFirst().orElse(null);
        if (cursoAtualizado == null) {
            throw new NoSuchElementException("Curso não encontrado");
        }
        cursoAtualizado.setNome(curso.getNome());
        return cursoAtualizado;
    }

    public List<Curso> delete(int id) {
        List<Curso> cursos = this.obterCursos();
        Curso curso = cursos.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
        if (curso == null) {
            throw new NoSuchElementException("Curso não encontrado");
        }
        cursos.remove(curso);
        return cursos;
    }

    public Curso findByName(String nome) {
        List<Curso> cursos = this.obterCursos();
        Curso curso = cursos.stream().filter(c -> c.getNome().equalsIgnoreCase(nome)).findFirst().orElse(null);
        if (curso == null) {
            throw new NoSuchElementException("Curso não encontrado");
        }
        return curso;
    }
}
