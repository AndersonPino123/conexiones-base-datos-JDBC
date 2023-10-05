package Classes;

import java.sql.DriverManager;
import java.sql.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public class eliminarCliente {
    
        public static void eliminarUsuario(String[] args) {

        //Conexión a SQL
        String usuario = "root";
        String password = "";
        String url = "jdbc:mysql://localhost:3306/lociones";
        Connection conexion;
        Statement statement;
        ResultSet rs;
    
        //Codigo para editar el usuario
        int idUsuario = 0;

        System.out.println("Ingrese el ID del usuario a eliminar:");
        Scanner id = new Scanner(System.in);
        idUsuario = id.nextInt();
        
                //consulta del usuario a eliminar
        try {
            conexion = DriverManager.getConnection(url, usuario, password);
           statement = conexion.createStatement();
            
            //realiza la consulta del usuario que se va a eliminar  con el siguiente query
            rs = statement.executeQuery("SELECT * FROM tbl_cliente WHERE idCliente = '" + idUsuario + "'");
            rs.next();
            
            do { //imprime o muestra que es lo que se obtuvo de la consulta anterior
                System.out.println("\nEl usuario a eliminar es:\n");
                System.out.println("ID CLIENTE: " + rs.getInt("idCliente") + "\nNOMBRE: " + rs.getString("Nombre") + "\nTELEFONO: " + rs.getString("Telefono"));
            } while (rs.next());
        }
         catch (SQLException ex) {
            Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("\n¿Esta seguro de que quiere eliminar este usuario? \nIngrese 1 = SI || 2 = NO");
        Scanner ingresoDesicion = new Scanner(System.in);
        int desicion = ingresoDesicion.nextInt(); //
        
                if (desicion == 1){

            try {
                conexion = DriverManager.getConnection(url, usuario, password);
                statement = conexion.createStatement();
                statement.executeUpdate("DELETE FROM tbl_cliente WHERE idCliente = '" + idUsuario + "'"); // como saber cuando llevan comillas dobles, simples y donde se coloca el más. 
                System.out.println("Usuario Eliminado de Manera Correcta"); 
                
                //Ejecutar el metodo main dentro de la clase para regresar al menú ppal
                MenuPrincipal cliente = new MenuPrincipal();
                cliente.main(args);
            } 
            
            catch (SQLException ex) {
                Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
           }
        else {
            
            //leer que no fue cancelado
            String telPersona = "";
            System.out.println("El usuario no fue eliminado: \n");  
                    
                    
            MenuPrincipal cliente = new MenuPrincipal();
            cliente.main(args);
        }
    }
 }