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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.mariadb.jdbc.Statement;
import universidad.entidades.Inscripcion;

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
}
