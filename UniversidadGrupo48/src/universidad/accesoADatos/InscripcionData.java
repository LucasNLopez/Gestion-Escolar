/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidad.accesoADatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.mariadb.jdbc.Statement;
import universidad.entidades.Alumno;
import universidad.entidades.Inscripcion;
import universidad.entidades.Materia;

/**
 *
 * @author Matias
 */
public class InscripcionData {
    private Connection conexion=null;
    private MateriaData md=new MateriaData();
    private AlumnoData ad=new AlumnoData();
    
    public InscripcionData(){
        conexion= Conexion.buscarConexion();
    }
    
    public void guardarInscripcion(Inscripcion inscripcion){
        String sql="INSERT INTO inscripcion idAlumno, idMateria "
                + "VALUES ?,?";
        try {
            PreparedStatement ps=conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, inscripcion.getAlumno().getIdAlumno());
            ps.setInt(2, inscripcion.getMateria().getIdMateria());
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                inscripcion.setIdInscripcion(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "Inscripcion Existosa.");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error " + ex.getMessage());
        }
    }
    
    public List<Inscripcion> obtenerInscripciones(){
        String sql="SELECT * FROM incripcion";
        
        List<Inscripcion> listaincripcion=new ArrayList<>();
        
        try {
            PreparedStatement ps=conexion.prepareStatement(sql);
            
            ResultSet rs=ps.executeQuery();
            Inscripcion inscripcion;
            while(rs.next()){
                inscripcion=new Inscripcion();
                inscripcion.setIdInscripcion(rs.getInt("idInscripto"));
                inscripcion.setAlumno(ad.buscarAlumno(rs.getInt("idAlumno")));
                inscripcion.setMateria(md.buscarMateria(rs.getInt("idMateria")));
                listaincripcion.add(inscripcion);
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error " + ex.getMessage());
        }
        return listaincripcion;
    }
    
    public List<Inscripcion> obtenerInscripcionesPorAlumno(int id){
        String sql="SELECT idIncripto,nota,idMateria FROM incripcion WHERE idAlumno=?";
        List<Inscripcion> incripciones=new ArrayList<>();
        try {
            PreparedStatement ps=conexion.prepareStatement(sql);
            ps.setInt(1, id);
            
            ResultSet rs=ps.executeQuery();
            Inscripcion inscripcion;
            while(rs.next()){
                inscripcion=new Inscripcion();
                inscripcion.setIdInscripcion(rs.getInt("idInscripto"));
                inscripcion.setNota(rs.getInt("nota"));
                inscripcion.setAlumno(ad.buscarAlumno(rs.getInt("idAlumno")));
                inscripcion.setMateria(md.buscarMateria(rs.getInt("idMateria")));
                incripciones.add(inscripcion);
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error " + ex.getMessage());
        }
        return incripciones;
    }
    
    public List<Materia> obtenerMateriasCursadas(int id){
        List<Materia> materias=new ArrayList<>();
        
        String sql="SELECT inscripcion.idMateria, nombre, año "
                + "FROM inscripcion JOIN materia ON (inscripcion.idMateria=materia.idMateria)"
                + "WHERE inscripcion.idAlumno = ?";
        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Materia materia=new Materia();
                materia.setIdMateria(rs.getInt("idMateria"));
                materia.setNombre(rs.getString("nombre"));
                materia.setAnioMateria(rs.getInt("año"));
                materias.add(materia);
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error " + ex.getMessage());
        }
        return materias;
    }
    
    public List<Materia> obtenerMateriasNoCursadas(int id){
        List<Materia> materias=new ArrayList<>();
        
        String sql="SELECT inscripcion.idMateria, nombre, año "
                + "FROM inscripcion JOIN materia ON (inscripcion.idMateria=materia.idMateria)"
                + "WHERE inscripcion.idAlumno != ?";
        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Materia materia=new Materia();
                materia.setIdMateria(rs.getInt("idMateria"));
                materia.setNombre(rs.getString("nombre"));
                materia.setAnioMateria(rs.getInt("año"));
                materias.add(materia);
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error " + ex.getMessage());
        }
        return materias;
    }
    
    public void borrarInscripcionMateriaAlumno(int idAlumno,int idMateria){
        String sql="DELETE FROM inscripcion WHERE idAlumno=? AND idMateria=?";
        
        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            ps.setInt(2, idMateria);
            
           int exito=ps.executeUpdate();
           
           if(exito==1){
               JOptionPane.showMessageDialog(null,"Inscripcion Eliminada.");
           }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error " + ex.getMessage());
        }
    }
    
    public void actualizarNota(int idAlumno, int idMateria, int nota){
        String sql="UPDATE inscripcion SET nota=? WHERE idAlumno=? AND idMateria=?";
        
        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, nota);
            ps.setInt(2, idAlumno);
            ps.setInt(3, idMateria);
            
           int exito=ps.executeUpdate();
           
           if(exito==1){
               JOptionPane.showMessageDialog(null,"Nota Actualizada.");
           }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error " + ex.getMessage());
        }
    }
    
    public List<Alumno> obtenerAlumnosXMateria(int idMateria){
        List<Alumno> alumnos=new ArrayList<>();
        AlumnoData ad=new AlumnoData();
        String sql="SELECT idAlumno FROM inscripcion WHERE idMateria=?";
        
        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, idMateria);
            
            ResultSet rs=ps.executeQuery();
            
            while(rs.next()){
                Alumno alumno=ad.buscarAlumno(rs.getInt("idAlumno"));
                alumnos.add(alumno);
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error " + ex.getMessage());
        }
        return alumnos;
    }
}
