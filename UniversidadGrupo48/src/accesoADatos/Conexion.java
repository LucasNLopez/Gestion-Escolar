
package accesoADatos;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexion {

    private static final String url = "jdbc:mariadb://localhost/universidad";
    private static final String usuario = "root";
    private static final String password = "";
    private static Connection conexion;

    private Conexion() {}

    public static Connection buscarConexion(){
        if (conexion==null){
            try{
                //cargamos las clases de mariadb que implementan JDBC
                Class.forName("org.mariadb.jdbc.Driver");
                conexion = DriverManager.getConnection(url, usuario, password);
            } catch (ClassNotFoundException | SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error "+ex.getMessage());
            }
        }
        return conexion; 
    }

}
