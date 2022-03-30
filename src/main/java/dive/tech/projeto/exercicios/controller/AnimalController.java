package dive.tech.projeto.exercicios.controller;

import dive.tech.projeto.exercicios.entity.Animal;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/animal")
public class AnimalController {

    @GET
    @Produces("text/plain")
    public String listarEspecies() {
        return "cachorro, gato, peixe";
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
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

    @GET
    @Path("/filtro")
    @Produces("application/json")
    public Response filtrarAnimais(@QueryParam("especie") String especie,
                                   @QueryParam("nome") String nome) {
        List<Animal> animais = List.of(
                new Animal(1L, "Abu", "Macaco"),
                new Animal(2L, "Bob", "Cachorro"),
                new Animal(3L, "Marcel", "Macaco"),
                new Animal(4L, "Sagua", "Gato"));

        List<Animal> filtrados = new ArrayList<>();

        if (especie == null && nome == null) {
            return Response.ok(animais).build();
        }

        for (Animal animal : animais) {
            if (especie != null && especie.equalsIgnoreCase(animal.getEspecie())) {
                filtrados.add(animal);
            }
            if (nome != null && nome.equalsIgnoreCase(animal.getNome())) {
                filtrados.add(animal);
            }
        }
        return Response.ok(filtrados).build();
    }
}
