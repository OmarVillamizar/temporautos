package Model.Dao;
import Model.Entity.Agencia;
import java.util.List;
import Model.Entity.Carro;
/**
 *
 * @author Omar Villamizar
 */
public interface IAgencia {
    public int Insertar(Agencia agencia);
    public  List<Agencia> consultar();
    public Agencia consultarId(Agencia agencia);
    public int borrar(Agencia agencia);
    public int actualizar(Agencia agencia);
    public List<Carro> consultarCarros(Agencia agencia);
}
