/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidad;

import java.sql.Connection;
import java.time.LocalDate;
import universidad.accesoADatos.AlumnoData;
import universidad.accesoADatos.Conexion;
import universidad.entidades.Alumno;

/**
 *
 * @author Matias
 */
public class universidadMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Alumno x=new Alumno(12345678,"Perez","Maria",LocalDate.of(1990, 5, 23),true);
        AlumnoData alu=new AlumnoData();
        alu.guardarAlumno(x);
    }
    
}
