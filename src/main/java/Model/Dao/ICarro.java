package Model.Dao;
import Model.Entity.Carro;
import java.util.List;
/**
 *
 * @author Omar Villamizar
 */
public interface ICarro {
    public int Insertar(Carro carro);
    public  List<Carro> consultar();
    public  Carro consultarId(Carro carro);
    public int borrar(Carro carro);
    public int actualizar(Carro carro);

}
