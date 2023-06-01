package api;
import Model.Dao.ClienteDao;
import Model.Entity.Cliente;
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
@Path("/apicliente")
public class ClienteResource {
    ClienteDao clienteDao = new ClienteDao();
    @GET
    @Path("/cliente")
    @Produces(MediaType.APPLICATION_JSON)

    public Response consultar(){
        List<Cliente> clientes = new ArrayList<>();
        clientes= clienteDao.consultar();
        return Response
                .status(200)
                .header("Access-Control-Allow-Origin", "*")
                .entity(clientes)
                .build();

    }
    @GET
    @Path("/cliente/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarId(@PathParam("id") String id) {
        Cliente cliente = new Cliente(id);
        return Response
                .status(200)
                .header("Access-Control-Allow-Origin", "*")
                .entity(clienteDao.consultarId(cliente))
                .build();
    }

    @POST
    @Path("/cliente")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crear(Cliente cliente)
    {
        try{
            clienteDao.Insertar(cliente);
            return Response.status(Response.Status.CREATED).entity(cliente).build();
        }
        catch(Exception ex)
        {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        }
    }
    @DELETE
    @Path("/cliente/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response borrar(@PathParam("id") String id) {
        Cliente cliente = new Cliente(id);
        int i = clienteDao.borrar(cliente);
        if (i == 0) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity("Cliente not found")
                    .build();
        } else {
            return Response.ok("Correcto").build();
        }
    }
    @Path("/cliente")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizar(Cliente cliente) {
        try{
            clienteDao.actualizar(cliente);
            return Response.status(Response.Status.CREATED).entity(cliente).build();
        }
        catch(Exception ex)
        {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        }
    }
}
