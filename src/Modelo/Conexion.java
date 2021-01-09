package Modelo;

import Configuracion.Proper;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Declaracion de la clase Conexion se utiliza para realizar la conexion a una
 * base de de datos
 *
 * @author Angello Morales
 * @version 27/07/2018
 */
public class Conexion {

    //Variable para extraer la informacion del archivo conf.properties
    private Proper propiedad = new Proper();
    
    //Variables para conectar a la BD modificar segun la BD
    private String USERNAME = "root";
    private String PASSWORD = "";

    //ubicacion de la BD lectura del archivo conf.properties 
    private String HOST = propiedad.getProp().getProperty("server.HOST");
    private String PORT = propiedad.getProp().getProperty("server.PORT");

    //Nombre de la base de datos
    private String DATABASE = propiedad.getProp().getProperty("server.DATABASE");
    private String CLASSNAME = "com.mysql.jdbc.Driver";                         //Alternativo org.gjt.mm.mysql.Driver

    //Cadena de conexion
    private String URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE;

    //objeto conexion
    private Connection conexion = null;

    public Connection getConexion() {
        try {

            // ejecutar DRIVER MSQL para crear conexion
            Class.forName(CLASSNAME);

            //establecer conexion
            conexion = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            if (conexion != null) {
                System.out.println("Conexion Exitosa");
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("ERROR " + e);
        }
        return conexion;
    }

    //public Connection getConexion() {
      //  return conexion;
    //}

}
