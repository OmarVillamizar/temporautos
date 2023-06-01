package Test;
import Model.Dao.CarroDao;
import Model.Entity.Carro;
import Model.Entity.Agencia;
public class CarroTest {
    public static void main(String[] args) {
        CarroDao carroDao = new CarroDao();
        Carro c = new Carro("1");
        System.out.println("TEST CONSULTAR POR ID (1)");
        System.out.println("-----------------------------------");
        System.out.println(carroDao.consultarId(c).toString());
        System.out.println("-----------------------------------");
        System.out.println("TEST CONSULTA DE CARROS EXISTENTES");
        System.out.println("-----------------------------------");
        System.out.println(carroDao.consultar());
        System.out.println("-----------------------------------");
    }
}
