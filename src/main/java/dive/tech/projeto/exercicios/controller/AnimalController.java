package dive.tech.projeto.exercicios.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/animal")
public class AnimalController {

    @GET
    @Produces("text/plain")
    public String listarEspecies() {
        return "cachorro, gato, peixe";
    }
}
