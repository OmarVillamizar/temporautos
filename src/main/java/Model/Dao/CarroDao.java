package Model.Dao;
import Model.Entity.Carro;
import Red.BaseDatos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Model.Entity.Agencia;
/**
 *
 * @author Omar Villamizar
 */

public class CarroDao implements ICarro{
    final static String SQL_CONSULTAR = "SELECT * FROM carro";
    final static String SQL_INSERTAR = "INSERT INTO carro(id,modelo,marca,tipoDeCaja, id_agencia) VALUES(?,?,?,?,?)";
    final static String SQL_BORRAR = "DELETE FROM carro WHERE id=? ";
    final static String SQL_CONSULTARID = "SELECT * FROM carro WHERE id=?";
    final static String SQL_ACTUALIZAR = "UPDATE carro SET modelo = ?, marca= ?, tipoDeCaja=? WHERE id=?";

    public List<Carro> consultar() {
        Connection connection = null;
        PreparedStatement sentencia = null;
        ResultSet resultado = null;
        List<Carro> carros = new ArrayList<>();
        try {
            connection = BaseDatos.getConnection();
            sentencia = connection.prepareStatement(SQL_CONSULTAR);
            resultado = sentencia.executeQuery();
            while (resultado.next()) {
                String id = resultado.getString("id");
                String modelo = resultado.getString("modelo");
                String marca = resultado.getString("marca");
                String tipoDeCaja = resultado.getString("tipoDeCaja");
                Agencia a = new Agencia(resultado.getString("id_agencia"));
                Carro carro = new Carro(id, modelo, marca, tipoDeCaja, new AgenciaDao().consultarId(a));
                carros.add(carro);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CarroDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CarroDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                BaseDatos.close(resultado);
                BaseDatos.close(sentencia);
                BaseDatos.close(connection);
            } catch (SQLException ex) {
                Logger.getLogger(CarroDao.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return carros;
    }

    public int Insertar(Carro carro) {
        Connection connection = null;
        PreparedStatement sentencia = null;
        int resultado = 0;
        try {
            connection = BaseDatos.getConnection();
            sentencia = connection.prepareStatement(SQL_INSERTAR);
            sentencia.setString(1, carro.getId());
            sentencia.setString(2, carro.getModelo());
            sentencia.setString(3, carro.getMarca());
            sentencia.setString(4, carro.getTipoDeCaja());
            sentencia.setString(5, carro.getAgencia().getId());
            resultado = sentencia.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CarroDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CarroDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }

    public int borrar(Carro carro) {
        Connection connection = null;
        PreparedStatement sentencia = null;
        int resultado = 0;
        try {
            connection = BaseDatos.getConnection();
            sentencia = connection.prepareStatement(SQL_BORRAR);
            sentencia.setString(1, carro.getId());
            resultado = sentencia.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CarroDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CarroDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }

    public Carro consultarId(Carro carro) {
        Connection connection = null;
        PreparedStatement sentencia = null;
        ResultSet resultado = null;
        Carro rCarro = null;
        try {
            connection = BaseDatos.getConnection();
            sentencia = connection.prepareStatement(SQL_CONSULTARID, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.TYPE_FORWARD_ONLY);
            sentencia.setString(1, carro.getId());
            resultado = sentencia.executeQuery();
            resultado.absolute(1);
            String id = resultado.getString("id");
            String modelo = resultado.getString("modelo");
            String marca = resultado.getString("marca");
            String tipoDeCaja = resultado.getString("tipoDeCaja");
            Agencia a = new Agencia(resultado.getString("id_agencia"));
            rCarro = new Carro(id, modelo, marca, tipoDeCaja, new AgenciaDao().consultarId(a));
        } catch (SQLException ex) {
            Logger.getLogger(CarroDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CarroDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                BaseDatos.close(resultado);
                BaseDatos.close(sentencia);
                BaseDatos.close(connection);
            } catch (SQLException ex) {
                Logger.getLogger(CarroDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return rCarro;
    }

    public int actualizar(Carro carro) {
        Connection connection = null;
        PreparedStatement sentencia = null;
        int resultado = 0;
        try {
            connection = BaseDatos.getConnection();
            sentencia = connection.prepareStatement(SQL_ACTUALIZAR);
            sentencia.setString(4, carro.getId());
            sentencia.setString(1, carro.getMarca());
            sentencia.setString(2, carro.getModelo());
            sentencia.setString(3, carro.getTipoDeCaja());
            resultado = sentencia.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CarroDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CarroDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }


}
