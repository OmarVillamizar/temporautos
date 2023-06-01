package Test;

import Model.Entity.Agencia;
import Model.Dao.AgenciaDao;
import Model.Entity.Carro;

import java.util.List;

public class AgenciaTest {
    public static void main(String[] args) {
        AgenciaDao agenciaDao = new AgenciaDao();
        Agencia a = new Agencia("2");
        Agencia b = new Agencia("1");
        System.out.println("-----------------------------------");
        List<Carro> lista = agenciaDao.consultarCarros(a);
        System.out.println("TEST CONSULTAR POR ID DE AGENCIA (1)");
        System.out.println("-----------------------------------");
        System.out.println("");
        System.out.println(agenciaDao.consultarId(b).toString());
        System.out.println("-----------------------------------");
        System.out.println("CARROS DE AGENCIA 2:");
        System.out.println("-----------------------------------");
        System.out.println("");
        for(Carro c : lista){
            System.out.println(c);
        }
        System.out.println("-----------------------------------");
    }
}
