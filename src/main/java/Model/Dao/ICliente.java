package Model.Dao;
import Model.Entity.Cliente;
import java.util.List;
/**
 *
 * @author Omar Villamizar
 */
public interface ICliente {

    public int Insertar(Cliente cliente);
    public  List<Cliente> consultar();
    public  Cliente consultarId(Cliente cliente);
    public int borrar(Cliente cliente);
    public int actualizar(Cliente cliente);
}
