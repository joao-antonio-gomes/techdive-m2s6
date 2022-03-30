package dive.tech.projeto.exercicios.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/animal")
public class AnimalController {

    @GET
    @Produces("text/plain")
    public String listarEspecies() {
        return "cachorro, gato, peixe";
    }

    @GET
    @Path("/{id}")
    public Response buscaranimalPorId(@PathParam("id") Long id) {
        String animal = null;
        if (id == 1) {
            animal = "Macaco";
        }
        if (id == 2) {
            animal = "Cachorro";
        }
        if (id == 3) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok(animal).build();
    }
}
