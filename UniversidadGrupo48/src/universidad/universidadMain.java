/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidad;


import java.time.LocalDate;
import universidad.accesoADatos.AlumnoData;
import universidad.accesoADatos.MateriaData;
import universidad.entidades.Alumno;
import universidad.entidades.Materia;

/**
 *
 * @author Matias
 */
public class universidadMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Alumno x=new Alumno(12345678,"Perez","Maria",LocalDate.of(1990, 5, 23),true);
        //AlumnoData alu=new AlumnoData();
        //alu.guardarAlumno(x);
        //alu.eliminarAlumno(1);
        //Materia x=new Materia("Matematica",2,true);
        MateriaData mat=new MateriaData();
        //mat.eliminarMateria(1);
        /*for(Materia materia:mat.listarMaterias()){
            System.out.println("Nombre: "+materia.getNombre());
            System.out.println("Año: "+materia.getAnioMateria());
        }*/
        /*Materia materia=mat.buscarCualquierMateria(1);
        System.out.println("Nombre: "+materia.getNombre());
        System.out.println("Año: "+materia.getAnioMateria());
        System.out.println("Estado: "+materia.isEstado());*/
    }
    
}
