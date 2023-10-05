package Classes;

import java.sql.Connection;              // igual 
import java.sql.DriverManager;
import java.sql.ResultSet;                // igual 
import java.sql.SQLException;            // igual
import java.sql.Statement;              // que es Statement y por que no esta en los demás clsaes?
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public class agregarCliente {

    public void addUser(String[] args) {

        //Conexión a SQL
        String usuario = "root";
        String password = "";
        String url = "jdbc:mysql://localhost:3306/lociones";
        Connection conexion;
        Statement statement;
        ResultSet rs;

        //intentar crear este primero con Class.forName("com.mysql.cj.jdbc.Driver"); y despues arreglarlo con la segunda opción
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        //leer el  nombre de la persona
        String nombrePersona = "";
        System.out.println("Ingrese el nombre: \n");
        Scanner nombre = new Scanner(System.in);
        nombrePersona = nombre.nextLine();

        //leer el telefono de la persona
        String telPersona = "";
        System.out.println("Ingrese el telefono: \n");
        Scanner telefono = new Scanner(System.in);
        telPersona = telefono.nextLine();

        // intentar nuevamanete con conexion = DriverManager.getConnection(url,usuario,password); para evitar errores               
        try {
            conexion = DriverManager.getConnection(url, usuario, password);
            statement = conexion.createStatement();
            statement.executeUpdate("INSERT INTO tbl_cliente(Nombre,Telefono) VALUES ('" + nombrePersona + "','" + telPersona + "')");
            System.out.println("Usuario Ingresado de Manera Correcta");
            MenuPrincipal cliente = new MenuPrincipal();
            cliente.main(args);
        } catch (SQLException ex) {
            Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
