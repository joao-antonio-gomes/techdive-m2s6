package dive.tech.projeto.exercicios.controller;

import dive.tech.projeto.exercicios.entity.Animal;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response cadastrarAnimal(Animal animal, @Context HttpHeaders headers) {
        //validação feito em 2 etapas para não dar erro caso não tenha nada no cabeçalho de autorizacao
        List<String> cabecalhoAutorizacao = headers.getRequestHeader(HttpHeaders.AUTHORIZATION);
        if (cabecalhoAutorizacao == null || cabecalhoAutorizacao.isEmpty()) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        String codigoAutorizacao = cabecalhoAutorizacao.get(0).substring(7);
        if (!codigoAutorizacao.equals("codigo123")) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        int id = 1 + (int)(Math.random() * ((1000 - 1) + 1));
        Animal animalCriado = new Animal((long) id, animal.getNome(), animal.getEspecie());
        return Response.ok(animalCriado).build();
    }

    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public Response atualizarAnimal(@Valid Animal animal) {
        List<Animal> animais = List.of(
                new Animal(1L, "Abu", "Macaco"),
                new Animal(2L, "Bob", "Cachorro"),
                new Animal(3L, "Marcel", "Macaco"),
                new Animal(4L, "Sagua", "Gato"));

        Long id = animal.getId();
        Animal animalEscolhido = null;
        for (Animal animalAtual : animais) {
            if (animalAtual.getId().equals(id)) {
                animalEscolhido = animalAtual;
            }
        }
        if (animalEscolhido == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(animal).build();
    }
}
