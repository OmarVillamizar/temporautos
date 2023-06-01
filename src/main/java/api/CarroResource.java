package api;
import Model.Dao.CarroDao;
import Model.Entity.Carro;
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
    @Path("/apicarro")
    public class CarroResource {
        CarroDao carroDao = new CarroDao();
        @GET
        @Path("/carro")
        @Produces(MediaType.APPLICATION_JSON)

        public Response consultar(){
            List<Carro> carros = new ArrayList<>();
            carros= carroDao.consultar();
            return Response
                    .status(200)
                    .header("Access-Control-Allow-Origin", "*")
                    .entity(carros)
                    .build();

        }
        @GET
        @Path("/carro/{id}")
        @Produces(MediaType.APPLICATION_JSON)
        public Response consultarId(@PathParam("id") String id) {
            Carro carro = new Carro(id);
            return Response
                    .status(200)
                    .header("Access-Control-Allow-Origin", "*")
                    .entity(carroDao.consultarId(carro))
                    .build();
        }

        @POST
        @Path("/carro")
        @Produces(MediaType.APPLICATION_JSON)
        @Consumes(MediaType.APPLICATION_JSON)
        public Response crear(Carro carro)
        {
            try{
                carroDao.Insertar(carro);
                return Response.status(Response.Status.CREATED).entity(carro).build();
            }
            catch(Exception ex)
            {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
            }
        }
        @DELETE
        @Path("/carro/{id}")
        @Produces(MediaType.APPLICATION_JSON)
        public Response borrar(@PathParam("id") String id) {
            Carro carro = new Carro(id);
            int i = carroDao.borrar(carro);
            if (i == 0) {
                return Response
                        .status(Response.Status.BAD_REQUEST)
                        .entity("Carro not found")
                        .build();
            } else {
                return Response.ok("Correcto").build();
            }
        }
        @Path("/carro")
        @PUT
        @Produces(MediaType.APPLICATION_JSON)
        @Consumes(MediaType.APPLICATION_JSON)
        public Response actualizar(Carro carro) {
            try{
                carroDao.actualizar(carro);
                return Response.status(Response.Status.CREATED).entity(carro).build();
            }
            catch(Exception ex)
            {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
            }
        }
}
