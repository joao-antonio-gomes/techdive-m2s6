package dive.tech.projeto.exercicios.controller;

import dive.tech.projeto.exercicios.dao.CursoDao;
import dive.tech.projeto.exercicios.entity.Curso;
import dive.tech.projeto.exercicios.entity.Disciplina;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.NoSuchElementException;

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

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response listById(@PathParam("id") int id) {
        try {
            Curso curso = this.cursoDao.listById(id);
            return Response.ok(curso).build();
        } catch (NoSuchElementException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public Response update(Curso curso) {
        try {
            Curso cursoAtualizado = this.cursoDao.update(curso);
            return Response.ok(cursoAtualizado).build();
        } catch (NoSuchElementException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
