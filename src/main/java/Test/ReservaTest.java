package Test;

import Model.Dao.ReservaDao;
import Model.Entity.Reserva;
import Model.Entity.Cliente;
import Model.Entity.Carro;

import java.util.List;

public class ReservaTest {
    public static void main(String[] args) {
        ReservaDao reservaDao = new ReservaDao();
        Reserva rb = new Reserva("4");
        Cliente c = new Cliente("1");
        List<Reserva> listaReserva = reservaDao.consultarReservas(c);
        System.out.println("TEST CONSULTAR POR ID DE RESERVA (4)");
        System.out.println("-----------------------------------");
        System.out.println("");
        System.out.println(reservaDao.consultarId(rb).toString());
        System.out.println("-----------------------------------");
        System.out.println("AGENCIAS POR ID DE CLIENTE: (1)");
        for(Reserva r : listaReserva){
            System.out.println(r);
        }
        System.out.println("-----------------------------------");
    }
}
