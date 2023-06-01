package Test;

import Model.Dao.ClienteDao;
import Model.Entity.Cliente;

public class ClienteTest {
    public static void main(String[] args) {
        ClienteDao clienteDao = new ClienteDao();
        Cliente c = new Cliente("2");
        System.out.println("TEST CONSULTAR POR ID (2)");
        System.out.println("-----------------------------------");
        System.out.println(clienteDao.consultarId(c).toString());
        System.out.println("-----------------------------------");
        System.out.println("TEST CONSULTA DE CLIENTES EXISTENTES");
        System.out.println("-----------------------------------");
        System.out.println(clienteDao.consultar());
        System.out.println("-----------------------------------");
    }
}
