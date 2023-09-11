
package universidad.accesoADatos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import org.mariadb.jdbc.Statement;
import universidad.entidades.Alumno;


public class AlumnoData {
   private Connection conexion=null;
   
   public AlumnoData(){
       conexion=Conexion.buscarConexion();
   }
   
   public void guardarAlumno(Alumno alumno){
       
       String sql="INSERT INTO alumno (dni, apellido, nombre, FechaNacimiento, estado)"
               + "VALUES (?, ?, ?, ?, ?)";
       try {
           PreparedStatement ps=conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
           ps.setInt(1, alumno.getDni());
           ps.setString(2, alumno.getApellido());
           ps.setString(3, alumno.getNombre());
           ps.setDate(4, Date.valueOf(alumno.getFechaDeNacimiento()));
           ps.setBoolean(5, alumno.isEstado());
           ps.executeUpdate();
           
           ResultSet rs=ps.getGeneratedKeys();
           if(rs.next()){
               alumno.setIdAlumno(rs.getInt(1));
               JOptionPane.showMessageDialog(null, "Alumno Guardado.");
           }
           ps.close();
           
       } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null,"Error "+ex.getMessage());
       }
       
       
   }
}
