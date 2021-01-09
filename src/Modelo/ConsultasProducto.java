package Modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Declaracion de la clase Consultas que hereda de la clase Conexion
 *
 * @author Angello Morales
 * @version 27/07/2018 Se utiliza para realizar consultas o registrar datos en
 * una BD
 */
public class ConsultasProducto extends Conexion {

    /**
     * --READ SQL STATEMENT--
     *
     * NOTA: para modificar se debe agregar los metodos sentencia.setString(1,
     * var1),sentencia.setString(2,var2);....sentencia.setString(N, varN);
 segun tabla manteniendo el orden
     *
     * @param pro
     * @return true si la consulta es exitosa
     * @throws java.sql.SQLException
     *
     */
    public boolean buscar(Producto pro) throws SQLException {
        PreparedStatement sentencia = null;
        ResultSet rs = null;
        String query = "SELECT * FROM producto WHERE codigo=?";
        
        try {
            sentencia = getConexion().prepareStatement(query);

            //ajusta parametros relacionados al simbolo "?"
            sentencia.setString(1, pro.getCodigo());
            //... agregar  esta linea para mas columnas, cambiar N por el numero
            //que continua
            //sentencia.setString(N, varN);

            //ejecuta la consulta
            rs = sentencia.executeQuery();

            //trae la consulta
            if(rs.next()){
              System.out.println("Consulta Exitosa");
              pro.setId(Integer.parseInt(rs.getString("id")));
              pro.setCodigo(rs.getString("codigo"));
              pro.setNombre(rs.getString("nombre"));
              pro.setPrecio(Double.parseDouble(rs.getString("precio")));
              pro.setCantidad(Integer.parseInt(rs.getString("cantidad")));
              return true;
            }

        } catch (SQLException e) {
            System.err.println("ERROR " + e);
        } finally {

            //Cierra conexion rs y sentencia
            if (getConexion() != null) {
                getConexion().close();
                System.out.println("Conexion Cerrada");
            }
            if (sentencia != null) {
                sentencia.close();
            }
            if (rs != null) {
                rs.close();
            }
        }

        return false;
    }

    /**
     * --registrar--
     *
     * @param pro conexion con el modelo
     * @ NOTA: para modificar se debe agregar los metodos sentencia.setString(1,
     * var1),sentencia.setString(2,var2);.... sentencia.setString(N, varN);
     * segun tabla manteniendo el orden
     * @return true si el registro es exitoso
     * @throws java.sql.SQLException
     *
     */
    public boolean registrar(Producto pro) throws SQLException {

        PreparedStatement sentencia = null;
        String query = "INSERT INTO producto(codigo,nombre,precio,cantidad) VALUES(?,?,?,?)";
        try {

            sentencia = getConexion().prepareStatement(query);

            //ajusta parametros relacionados al simbolo "?"
            sentencia.setString(1, pro.getCodigo());
            sentencia.setString(2, pro.getNombre());
            sentencia.setDouble(3, pro.getPrecio());
            sentencia.setInt(4, pro.getCantidad());
            //... agregar  esta linea para mas columnas, cambiar N por el numero
            //que continua
            //sentencia.setString(N, varN);

            //Si se ejecuta un registro devuelve true
            if (sentencia.executeUpdate() == 1) {
                System.out.println("Registro Exitoso");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("ERROR " + e);
        } finally {
            try {
                //Cierra conexion y sentencia
                if (getConexion() != null) {
                    getConexion().close();
                    System.out.println("Conexion Cerrada");
                }
                if (sentencia != null) {
                    sentencia.close();
                }
            } catch (SQLException e) {
                System.err.println("ERROR " + e);
            }
        }

        return false;
    }

    public boolean modificar(Producto pro) throws SQLException {

        PreparedStatement sentencia = null;
        String query = "UPDATE producto SET codigo=?,nombre=?,precio=?,cantidad=? WHERE id=?";
        try {

            sentencia = getConexion().prepareStatement(query);

            //ajusta parametros relacionados al simbolo "?"
            sentencia.setString(1, pro.getCodigo());
            sentencia.setString(2, pro.getNombre());
            sentencia.setDouble(3, pro.getPrecio());
            sentencia.setInt(4, pro.getCantidad());
            sentencia.setInt(5, pro.getId());
            //... agregar  esta linea para mas columnas, cambiar N por el numero
            //que continua
            //sentencia.setString(N, varN);

            //Si se ejecuta un registro devuelve true
            if (sentencia.executeUpdate() == 1) {
                System.out.println("Registro Exitoso");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("ERROR " + e);
        } finally {
            try {
                //Cierra conexion y sentencia
                if (getConexion() != null) {
                    getConexion().close();
                    System.out.println("Conexion Cerrada");
                }
                if (sentencia != null) {
                    sentencia.close();
                }
            } catch (SQLException e) {
                System.err.println("ERROR " + e);
            }
        }

        return false;
    }

    public boolean eliminar(Producto pro) throws SQLException {

        PreparedStatement sentencia = null;
        String query = "DELETE FROM producto WHERE id=?";
        try {

            sentencia = getConexion().prepareStatement(query);

            //ajusta parametros relacionados al simbolo "?"
            sentencia.setInt(1, pro.getId());
            //... agregar  esta linea para mas columnas, cambiar N por el numero
            //que continua
            //sentencia.setString(N, varN);

            //Si se ejecuta un registro devuelve true
            if (sentencia.executeUpdate() == 1) {
                System.out.println("Registro Exitoso");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("ERROR " + e);
        } finally {
            try {
                //Cierra conexion y sentencia
                if (getConexion() != null) {
                    getConexion().close();
                    System.out.println("Conexion Cerrada");
                }
                if (sentencia != null) {
                    sentencia.close();
                }
            } catch (SQLException e) {
                System.err.println("ERROR " + e);
            }
        }

        return false;
    }

}
