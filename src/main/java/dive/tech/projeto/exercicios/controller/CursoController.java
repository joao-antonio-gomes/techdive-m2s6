package dive.tech.projeto.exercicios.controller;

import dive.tech.projeto.exercicios.dao.CursoDao;
import dive.tech.projeto.exercicios.entity.Curso;
import dive.tech.projeto.exercicios.entity.Disciplina;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/curso")
public class CursoController {
    private CursoDao cursoDao;

    public CursoController() {
        this.cursoDao = new CursoDao();
    }

    @GET
    @Produces("application/json")
    public List<Curso> listarCursos() {
        return this.cursoDao.obterCursos();
    }

    @POST
    @Produces("application/json")
    public Response salvarCurso(Curso curso) {
        Curso cursoCriado = this.cursoDao.salvarCurso(curso);
        return Response.status(Response.Status.CREATED).entity(cursoCriado).build();
    }
}
