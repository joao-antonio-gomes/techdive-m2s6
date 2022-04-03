package dive.tech.projeto.exercicios.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
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
}
