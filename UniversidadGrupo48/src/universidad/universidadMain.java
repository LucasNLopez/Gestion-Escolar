/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidad;

import java.sql.Connection;
import universidad.accesoADatos.Conexion;

/**
 *
 * @author Matias
 */
public class universidadMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Connection conexion=Conexion.buscarConexion();
        System.out.println("Exito.123.456.");
    }
    
}
