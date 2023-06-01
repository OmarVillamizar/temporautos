package Model.Dao;
import Model.Entity.Reserva;
import java.util.List;
import Model.Entity.Cliente;
/**
 *
 * @author Omar Villamizar
 */

public interface IReserva {
    public int Insertar(Reserva reserva);
    public List<Reserva> consultar();
    public Reserva consultarId(Reserva reserva);
    public int borrar(Reserva reserva);
    public int actualizar(Reserva reserva);
    public List<Reserva> consultarReservas(Cliente c);
}
