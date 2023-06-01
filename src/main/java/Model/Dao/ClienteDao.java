package Model.Dao;
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

public class ClienteDao implements ICliente {
    final static String SQL_CONSULTAR = "SELECT * FROM cliente";
    final static String SQL_INSERTAR = "INSERT INTO cliente(id,nombre,apellido,telefono,correo) VALUES(?,?,?,?,?)";
    final static String SQL_BORRAR = "DELETE FROM cliente WHERE id=? ";
    final static String SQL_CONSULTARID = "SELECT * FROM cliente WHERE id=?";
    final static String SQL_ACTUALIZAR = "UPDATE cliente SET nombre = ?, apellido= ?, telefono=?, correo=? WHERE id=?";

    public List<Cliente> consultar() {
        Connection connection = null;
        PreparedStatement sentencia = null;
        ResultSet resultado = null;
        List<Cliente> clientes = new ArrayList<>();
        try {
            connection = BaseDatos.getConnection();
            sentencia = connection.prepareStatement(SQL_CONSULTAR);
            resultado = sentencia.executeQuery();
            while (resultado.next()) {

                String id = resultado.getString("id");
                String nombre = resultado.getString("nombre");
                String apellido = resultado.getString("apellido");
                String telefono = resultado.getString("telefono");
                String correo = resultado.getString("correo");
                Cliente cliente = new Cliente(id, nombre, apellido, telefono,correo);
                clientes.add(cliente);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                BaseDatos.close(resultado);
                BaseDatos.close(sentencia);
                BaseDatos.close(connection);
            } catch (SQLException ex) {
                Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return clientes;
    }

    public int Insertar(Cliente cliente) {
        Connection connection = null;
        PreparedStatement sentencia = null;
        int resultado = 0;
        try {
            connection = BaseDatos.getConnection();
            sentencia = connection.prepareStatement(SQL_INSERTAR);
            sentencia.setString(1, cliente.getId());
            sentencia.setString(2, cliente.getNombre());
            sentencia.setString(3, cliente.getApellido());
            sentencia.setString(4, cliente.getTelefono());
            sentencia.setString(5, cliente.getCorreo());
            resultado = sentencia.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }

    public int borrar(Cliente cliente) {
        Connection connection = null;
        PreparedStatement sentencia = null;
        int resultado = 0;
        try {
            connection = BaseDatos.getConnection();
            sentencia = connection.prepareStatement(SQL_BORRAR);
            sentencia.setString(1, cliente.getId());
            resultado = sentencia.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }

    public Cliente consultarId(Cliente cliente) {
        Connection connection = null;
        PreparedStatement sentencia = null;
        ResultSet resultado = null;
        Cliente rCliente = null;
        try {
            connection = BaseDatos.getConnection();
            sentencia = connection.prepareStatement(SQL_CONSULTARID, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.TYPE_FORWARD_ONLY);
            sentencia.setString(1, cliente.getId());
            resultado = sentencia.executeQuery();
            resultado.absolute(1);
            String id = resultado.getString("id");
            String nombre = resultado.getString("nombre");
            String apellido = resultado.getString("apellido");
            String telefono = resultado.getString("telefono");
            String correo = resultado.getString("correo");
            rCliente = new Cliente(id, nombre, apellido, telefono,correo);
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                BaseDatos.close(resultado);
                BaseDatos.close(sentencia);
                BaseDatos.close(connection);
            } catch (SQLException ex) {
                Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return rCliente;
    }

    public int actualizar(Cliente cliente) {
        Connection connection = null;
        PreparedStatement sentencia = null;
        int resultado = 0;
        try {
            connection = BaseDatos.getConnection();
            sentencia = connection.prepareStatement(SQL_ACTUALIZAR);
            sentencia.setString(5, cliente.getId());
            sentencia.setString(1, cliente.getNombre());
            sentencia.setString(2, cliente.getApellido());
            sentencia.setString(3, cliente.getTelefono());
            sentencia.setString(4, cliente.getCorreo());
            resultado = sentencia.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }

}

