package Model.Dao;
import Model.Entity.Agencia;
import Model.Entity.Carro;
import Model.Entity.Reserva;
import Model.Entity.Cliente;
import Red.BaseDatos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Omar Villamizar
 */
public class ReservaDao {
    final static String SQL_CONSULTAR = "SELECT * FROM reserva";
    final static String SQL_INSERTAR = "INSERT INTO reserva(id,fechaInicio,fechaFin,id_cliente, id_carro) VALUES(?,?,?,?,?)";
    final static String SQL_BORRAR = "DELETE FROM reserva WHERE id=? ";
    final static String SQL_CONSULTARID = "SELECT * FROM reserva WHERE id=?";
    final static String SQL_ACTUALIZAR = "UPDATE reserva SET fechaInicio = ?, fechaFin = ? WHERE id=?";

    final static String SQL_CONSULTARRESERVAS = "SELECT * FROM reserva WHERE id_cliente = ?;";

    public List<Reserva> consultar() {
        Connection connection = null;
        PreparedStatement sentencia = null;
        ResultSet resultado = null;
        List<Reserva> reservas = new ArrayList<>();
        try {
            connection = BaseDatos.getConnection();
            sentencia = connection.prepareStatement(SQL_CONSULTAR);
            resultado = sentencia.executeQuery();
            while (resultado.next()) {

                String id = resultado.getString("id");
                String fechaInicio = resultado.getString("fechaInicio");
                String fechaFin = resultado.getString("fechaFin");
                Carro k = new Carro(resultado.getString("id_carro"));
                Cliente c = new Cliente(resultado.getString("id_cliente"));
                Reserva reserva = new Reserva(id, fechaInicio, fechaFin, new ClienteDao().consultarId(c),new CarroDao().consultarId(k));
                reservas.add(reserva);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ReservaDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReservaDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                BaseDatos.close(resultado);
                BaseDatos.close(sentencia);
                BaseDatos.close(connection);
            } catch (SQLException ex) {
                Logger.getLogger(ReservaDao.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return reservas;
    }

    public int Insertar(Reserva reserva) {
        Connection connection = null;
        PreparedStatement sentencia = null;
        int resultado = 0;
        try {
            connection = BaseDatos.getConnection();
            sentencia = connection.prepareStatement(SQL_INSERTAR);
            sentencia.setString(1, reserva.getId());
            sentencia.setString(2, reserva.getFechaInicio());
            sentencia.setString(3, reserva.getFechaFin());
            resultado = sentencia.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ReservaDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReservaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }

    public int borrar(Reserva reserva) {
        Connection connection = null;
        PreparedStatement sentencia = null;
        int resultado = 0;
        try {
            connection = BaseDatos.getConnection();
            sentencia = connection.prepareStatement(SQL_BORRAR);
            sentencia.setString(1, reserva.getId());
            resultado = sentencia.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ReservaDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReservaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }

    public Reserva consultarId(Reserva reserva) {
        Connection connection = null;
        PreparedStatement sentencia = null;
        ResultSet resultado = null;
        Reserva rReserva = null;
        try {
            connection = BaseDatos.getConnection();
            sentencia = connection.prepareStatement(SQL_CONSULTARID, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.TYPE_FORWARD_ONLY);
            sentencia.setString(1, reserva.getId());
            resultado = sentencia.executeQuery();
            resultado.absolute(1);
            String id = resultado.getString("id");
            String fechaInicio = resultado.getString("fechaInicio");
            String fechaFin = resultado.getString("fechaFin");
            Cliente c = new Cliente(resultado.getString("id_cliente"));
            Carro k = new Carro(resultado.getString("id_carro"));
            rReserva = new Reserva(id, fechaInicio, fechaFin, new ClienteDao().consultarId(c),new CarroDao().consultarId(k));
        } catch (SQLException ex) {
            Logger.getLogger(ReservaDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReservaDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                BaseDatos.close(resultado);
                BaseDatos.close(sentencia);
                BaseDatos.close(connection);
            } catch (SQLException ex) {
                Logger.getLogger(ReservaDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return rReserva;
    }

    public List<Reserva> consultarReservas(Cliente cliente) {
        Connection connection = null;
        PreparedStatement sentencia = null;
        ResultSet resultado = null;
        List<Reserva> reservas = new ArrayList<>();
        try {
            connection = BaseDatos.getConnection();
            sentencia = connection.prepareStatement(SQL_CONSULTARRESERVAS);
            sentencia.setString(1, cliente.getId());

            resultado = sentencia.executeQuery();
            while (resultado.next()) {
                String id = resultado.getString("id");
                String fechaInicio = resultado.getString("fechaInicio");
                String fechaFin = resultado.getString("fechaFin");
                //String id = resultado.getString("agencia.id");
                //String nombre = resultado.getString("nombre");
                //carro
                Cliente c = new Cliente(resultado.getString("id_cliente"));
                Carro k = new Carro(resultado.getString("id_carro"));
                Reserva reserva = new Reserva(id,fechaInicio,fechaFin, new ClienteDao().consultarId(c), new CarroDao().consultarId(k));
                reservas.add(reserva);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AgenciaDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AgenciaDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                BaseDatos.close(resultado);
                BaseDatos.close(sentencia);
                BaseDatos.close(connection);
            } catch (SQLException ex) {
                Logger.getLogger(AgenciaDao.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return reservas;
    }

    public int actualizar(Reserva reserva) {
        Connection connection = null;
        PreparedStatement sentencia = null;
        int resultado = 0;
        try {
            connection = BaseDatos.getConnection();
            sentencia = connection.prepareStatement(SQL_ACTUALIZAR);
            sentencia.setString(3, reserva.getId());
            sentencia.setString(1, reserva.getFechaInicio());
            sentencia.setString(2, reserva.getFechaFin());
            resultado = sentencia.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ReservaDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReservaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }
}
