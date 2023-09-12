
package universidad.accesoADatos;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
   
   public void modificarAlumno(Alumno alumno){
       
       String sql="UPDATE alumno SET dni=?, apellido=?, nombre=?, FechaNacimiento=?"
               + "WHERE idAlumno=?";
       
       try {
           PreparedStatement ps=conexion.prepareStatement(sql);
           ps.setInt(1, alumno.getDni());
           ps.setString(2, alumno.getApellido());
           ps.setString(3, alumno.getNombre());
           ps.setDate(4, Date.valueOf(alumno.getFechaDeNacimiento()));
           ps.setInt(5, alumno.getIdAlumno());
           int exito=ps.executeUpdate();
           if(exito==1){
               JOptionPane.showMessageDialog(null,"Alumno Modificado.");
           }
       } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null,"Error "+ex.getMessage());
       }
       
   }
   
   public void eliminarAlumno(int id){
       String sql="UPDATE alumno SET estado=0 WHERE idAlumno=?";
       
       try {
           PreparedStatement ps = conexion.prepareStatement(sql);
           ps.setInt(1, id);
           int exito=ps.executeUpdate();
           if(exito==1){
               JOptionPane.showMessageDialog(null,"Alumno Eliminado.");
           }
       } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null,"Error "+ex.getMessage());
       }
       
   }
   
   public Alumno buscarAlumno(int id){
       String sql="SELECT dni, apellido, nombre, FechaNacimiento FROM alumno WHERE idAlumno=? AND estado=1";
       Alumno alumno=null;
       try {
           PreparedStatement ps=conexion.prepareStatement(sql);
           ps.setInt(1, id);
           ResultSet rs=ps.executeQuery();
           if(rs.next()){
               alumno=new Alumno();
               alumno.setIdAlumno(id);
               alumno.setDni(rs.getInt("dni"));
               alumno.setApellido(rs.getString("apellido"));
               alumno.setNombre(rs.getString("nombre"));
               alumno.setFechaDeNacimiento(rs.getDate("FechaNacieminto").toLocalDate());
               alumno.setEstado(true);
           }else{
               JOptionPane.showMessageDialog(null,"No existe un alumno con ese id.");
           }
           ps.close();
       } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null,"Error "+ex.getMessage());
       }
       return alumno;
   }
   public Alumno buscarAlumnoPorDni(int dni){
       String sql="SELECT idAlumno, dni, apellido, nombre, FechaNacimiento FROM alumno WHERE dni=? AND estado=1";
       Alumno alumno=null;
       try {
           PreparedStatement ps=conexion.prepareStatement(sql);
           ps.setInt(1, dni);
           ResultSet rs=ps.executeQuery();
           if(rs.next()){
               alumno=new Alumno();
               alumno.setIdAlumno(rs.getInt("idAlumno"));
               alumno.setDni(dni);
               alumno.setApellido(rs.getString("apellido"));
               alumno.setNombre(rs.getString("nombre"));
               alumno.setFechaDeNacimiento(rs.getDate("FechaNacieminto").toLocalDate());
               alumno.setEstado(true);
           }else{
               JOptionPane.showMessageDialog(null,"No existe un alumno con ese id.");
           }
           ps.close();
       } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null,"Error "+ex.getMessage());
       }
       return alumno;
   }
   
   public List<Alumno> listarAlumnos(){
       String sql="SELECT idAlumno, dni, apellido, nombre, FechaNacimiento FROM alumno WHERE estado=1";
       ArrayList<Alumno> alumnos=new ArrayList<>();
       try {
           PreparedStatement ps=conexion.prepareStatement(sql);
           
           ResultSet rs=ps.executeQuery();
           while(rs.next()){
               Alumno alumno=new Alumno();
               alumno.setIdAlumno(rs.getInt("idAlumno"));
               alumno.setDni(rs.getInt("dni"));
               alumno.setApellido(rs.getString("apellido"));
               alumno.setNombre(rs.getString("nombre"));
               alumno.setFechaDeNacimiento(rs.getDate("FechaNacieminto").toLocalDate());
               alumno.setEstado(true);
               
               alumnos.add(alumno);
           }
           ps.close();
       } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null,"Error "+ex.getMessage());
       }
       return alumnos;
   }
}
