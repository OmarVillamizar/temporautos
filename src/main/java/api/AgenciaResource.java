package api;
import Model.Dao.AgenciaDao;
import Model.Entity.Agencia;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import static jakarta.ws.rs.core.Response.status;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Omar Villamizar
 */
@Path("/apiagencia")
public class AgenciaResource {
    AgenciaDao agenciaDao = new AgenciaDao();
    @GET
    @Path("/agencia")
    @Produces(MediaType.APPLICATION_JSON)

    public Response consultar(){
        List<Agencia> agencias= new ArrayList<>();
        agencias= agenciaDao.consultar();
        return Response
                .status(200)
                .header("Access-Control-Allow-Origin", "*")
                .entity(agencias)
                .build();

    }
    @GET
    @Path("/agencia/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarId(@PathParam("id") String id) {
        Agencia tienda = new Agencia(id);
        return Response
                .status(200)
                .header("Access-Control-Allow-Origin", "*")
                .entity(agenciaDao.consultarId(tienda))
                .build();
    }

    @POST
    @Path("/agencia")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crear(Agencia agencia)
    {
        try{
            agenciaDao.Insertar(agencia);
            return Response.status(Response.Status.CREATED).entity(agencia).build();
        }
        catch(Exception ex)
        {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        }
    }
    @DELETE
    @Path("/agencia/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response borrar(@PathParam("id") String id) {
        Agencia agencia = new Agencia(id);
        int i = agenciaDao.borrar(agencia);
        if (i == 0) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity("agencia not found")
                    .build();
        } else {
            return Response.ok("Correcto").build();
        }
    }
    @Path("/agencia")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizar(Agencia agencia) {
        try{
            agenciaDao.actualizar(agencia);
            return Response.status(Response.Status.CREATED).entity(agencia).build();
        }
        catch(Exception ex)
        {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        }
    }
}
