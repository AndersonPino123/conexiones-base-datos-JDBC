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
public class editarCliente {

    public static void editarUsuario(String[] args) {

        //Conexión a SQL
        String usuario = "root";
        String password = "";
        String url = "jdbc:mysql://localhost:3306/lociones";
        Connection conexion;
        Statement statement;
        ResultSet rs;

        //Codigo para editar el usuario
        int idUsuario = 0;

        System.out.println("Ingrese el ID del usuario a editar:");
        Scanner id = new Scanner(System.in);
        idUsuario = id.nextInt();

        //consulta del usuario a editar
        try {
            conexion = DriverManager.getConnection(url, usuario, password);
            statement = conexion.createStatement();
            //realiza la consulta del usuario que se va a editar con el siguiente query
            rs = statement.executeQuery("SELECT * FROM tbl_cliente WHERE idCliente = '" + idUsuario + "'");
            rs.next();
            do { //imprime o muestra que es lo que se obtuvo de la consulta anterior
                System.out.println("\nEl usuario a editar es:\n");
                System.out.println("ID CLIENTE: " + rs.getInt("idCliente") + "\nNOMBRE: " + rs.getString("Nombre") + "\nTELEFONO: " + rs.getString("Telefono"));
            } while (rs.next());
        } 
        
        catch (SQLException ex) {
            Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("\n¿Esta seguro de que quiere editar este usuario? \nIngrese 1 = SI || 2 = NO");
        Scanner ingresoDesicion = new Scanner(System.in);
        int desicion = ingresoDesicion.nextInt();

        if (desicion == 1) {
            //leer el  nombre de la persona
            String nombrePersona = "";
            System.out.println("Ingrese el nuevo nombre: \n");
            Scanner nombre = new Scanner(System.in);
            nombrePersona = nombre.nextLine();

            //leer el telefono de la persona
            String telPersona = "";
            System.out.println("Ingrese el nuevo telefono: \n");
            Scanner telefono = new Scanner(System.in);
            telPersona = telefono.nextLine();

            try {
                conexion = DriverManager.getConnection(url, usuario, password);
                statement = conexion.createStatement();
                statement.executeUpdate("UPDATE tbl_cliente SET Nombre = '" + nombrePersona + "', Telefono = '" + telPersona + "' WHERE idCliente = '" + idUsuario + "'");
                System.out.println("Usuario Actualizado de Manera Correcta");
                
                //Ejecutar el metodo main dentro de la clase para regresar al menú ppal
                MenuPrincipal cliente = new MenuPrincipal();
                cliente.main(args);
            } 
            
            catch (SQLException ex) {
                Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        
        else {
            MenuPrincipal cliente = new MenuPrincipal();
            cliente.main(args);
        }
    }
}
