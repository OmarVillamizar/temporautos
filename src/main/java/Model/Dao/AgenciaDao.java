package Model.Dao;
import Model.Entity.Agencia;
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
/**
 *
 * @author Omar Villamizar
 */
public class AgenciaDao implements IAgencia{
    final static String SQL_CONSULTAR = "SELECT * FROM agencia";
    final static String SQL_INSERTAR = "INSERT INTO agencia(id,nombre) VALUES(?,?)";
    final static String SQL_BORRAR = "DELETE FROM agencia WHERE id=? ";
    final static String SQL_CONSULTARID = "SELECT * FROM agencia WHERE id=?";
    final static String SQL_ACTUALIZAR = "UPDATE agencia SET nombre = ? WHERE id=?";
    final static String SQL_CONSULTARCARROS = "SELECT * FROM carro WHERE carro.id_agencia = ?;";

    public List<Carro> consultarCarros(Agencia agencia) {
        Connection connection = null;
        PreparedStatement sentencia = null;
        ResultSet resultado = null;
        List<Carro> carros = new ArrayList<>();
        try {
            connection = BaseDatos.getConnection();
            sentencia = connection.prepareStatement(SQL_CONSULTARCARROS);
            sentencia.setString(1, agencia.getId());

            resultado = sentencia.executeQuery();
            while (resultado.next()) {
                String id = resultado.getString("id");
                String modelo = resultado.getString("modelo");
                String marca = resultado.getString("marca");
                String tipoDeCaja = resultado.getString("tipoDeCaja");
                Agencia a = new Agencia(resultado.getString("id_agencia"));
                Carro carro = new Carro(id, modelo, marca, tipoDeCaja, new AgenciaDao().consultarId(a));
                //String id = resultado.getString("agencia.id");
                //String nombre = resultado.getString("nombre");
                //carro
                carros.add(carro);
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
        return carros;
    }

    public List<Agencia> consultar() {
        Connection connection = null;
        PreparedStatement sentencia = null;
        ResultSet resultado = null;
        List<Agencia> agencias = new ArrayList<>();
        try {
            connection = BaseDatos.getConnection();
            sentencia = connection.prepareStatement(SQL_CONSULTAR);
            resultado = sentencia.executeQuery();
            while (resultado.next()) {
                String id = resultado.getString("id");
                String nombre = resultado.getString("nombre");
                Agencia agencia = new Agencia(id,nombre);
                agencias.add(agencia);
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
        return agencias;
    }

    public int Insertar(Agencia agencia) {
        Connection connection = null;
        PreparedStatement sentencia = null;
        int resultado = 0;
        try {
            connection = BaseDatos.getConnection();
            sentencia = connection.prepareStatement(SQL_INSERTAR);
            sentencia.setString(1, agencia.getId());
            sentencia.setString(2, agencia.getNombre());
            resultado = sentencia.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(AgenciaDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AgenciaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }

    public int borrar(Agencia agencia) {
        Connection connection = null;
        PreparedStatement sentencia = null;
        int resultado = 0;
        try {
            connection = BaseDatos.getConnection();
            sentencia = connection.prepareStatement(SQL_BORRAR);
            sentencia.setString(1, agencia.getId());
            resultado = sentencia.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(AgenciaDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AgenciaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }

    public Agencia consultarId(Agencia agencia) {
        Connection connection = null;
        PreparedStatement sentencia = null;
        ResultSet resultado = null;
        Agencia rAgencia = null;
        try {
            connection = BaseDatos.getConnection();
            sentencia = connection.prepareStatement(SQL_CONSULTARID, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.TYPE_FORWARD_ONLY);
            sentencia.setString(1, agencia.getId());
            resultado = sentencia.executeQuery();
            resultado.absolute(1);
            String id = resultado.getString("id");
            String nombre = resultado.getString("nombre");
            rAgencia = new Agencia(id,nombre);
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
        return rAgencia;
    }

    public int actualizar(Agencia agencia) {
        Connection connection = null;
        PreparedStatement sentencia = null;
        int resultado = 0;
        try {
            connection = BaseDatos.getConnection();
            sentencia = connection.prepareStatement(SQL_ACTUALIZAR);
            sentencia.setString(2, agencia.getId());
            sentencia.setString(1, agencia.getNombre());
            resultado = sentencia.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(AgenciaDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AgenciaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }

}
