package dive.tech.projeto.exercicios.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

@Path("/curso")
public class CursoController {
    private List<String> cursos = List.of("Java", "Python", "C#", "C++");

    @GET
    @Produces("text/plain")
    public List<String> listarCursos() {
        return cursos;
    }
}
