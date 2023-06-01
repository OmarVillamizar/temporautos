package api;
import Model.Dao.ReservaDao;
import Model.Entity.Reserva;
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
@Path("/apireserva")
public class ReservaResource {
    ReservaDao reservaDao = new ReservaDao();
    @GET
    @Path("/reserva")
    @Produces(MediaType.APPLICATION_JSON)

    public Response consultar(){
        List<Reserva> reservas= new ArrayList<>();
        reservas= reservaDao.consultar();
        return Response
                .status(200)
                .header("Access-Control-Allow-Origin", "*")
                .entity(reservas)
                .build();

    }
    @GET
    @Path("/reserva/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarId(@PathParam("id") String id) {
        Reserva tienda = new Reserva(id);
        return Response
                .status(200)
                .header("Access-Control-Allow-Origin", "*")
                .entity(reservaDao.consultarId(tienda))
                .build();
    }

    @POST
    @Path("/reserva")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crear(Reserva reserva)
    {
        try{
            reservaDao.Insertar(reserva);
            return Response.status(Response.Status.CREATED).entity(reserva).build();
        }
        catch(Exception ex)
        {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        }
    }
    @DELETE
    @Path("/reserva/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response borrar(@PathParam("id") String id) {
        Reserva reserva = new Reserva(id);
        int i = reservaDao.borrar(reserva);
        if (i == 0) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity("Reserva not found")
                    .build();
        } else {
            return Response.ok("Correcto").build();
        }
    }
    @Path("/reserva")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizar(Reserva reserva) {
        try{
            reservaDao.actualizar(reserva);
            return Response.status(Response.Status.CREATED).entity(reserva).build();
        }
        catch(Exception ex)
        {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        }
    }
}
