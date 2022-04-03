package dive.tech.projeto.exercicios.controller;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.HashMap;

@Path("/sistema")
public class SistemaController {

    @GET
    @Produces("text/plain")
    public String getHeaderOfRequest(@Context HttpHeaders headers) {
        HashMap<String, String> headersList = new HashMap<>();
        headers.getRequestHeaders().forEach((key, value) -> {
            headersList.put(key, value.get(0));
        });
        return headersList.toString();
    }

    @POST
    @Path("/cookie")
    @Consumes("application/x-www-form-urlencoded")
    @Produces("application/json")
    public Response generateCookies(@FormParam("valor") String valor) {
        NewCookie cookie = new NewCookie("meuCookie", valor);
        return Response.ok(cookie).cookie().build();
    }

    @GET
    @Path("/cookie")
    @Produces("application/json")
    public Response getCookie(@CookieParam("meuCookie") String cookie) {
        try {
            return Response.ok(cookie).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}

