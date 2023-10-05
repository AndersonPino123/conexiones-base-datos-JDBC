package Classes;

/**
 *
 * @author USER
 */
import java.sql.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MenuPrincipal {

    public static void main(String[] args) {

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

        int opcion = 0; //variable para la opción que se ingrese en el menú
        Scanner opcionIngresada = new Scanner(System.in); //Se utiliza Scanner para la entrada por teclado de la opción
        System.out.println("Bienvenido a Perfumes Anderson "
                + "\nElija una opción del menú para continuar\n\n"
                + "1-Agregar Usuario\n"
                + "2-Consultar Usuarios\n"
                + "3-Editar Usuario\n"
                + "4-Eliminar Usuario\n"
                + "5-Salir");
        opcion = opcionIngresada.nextInt();//Se le asigna el valor ingresado por teclado a la variable opcion

        //switch case para validar cual fue la opción ingresada por el usuario
        switch (opcion) {
            case 1:
                agregarCliente addCx = new agregarCliente();
                addCx.addUser(args);
                        
                break;

            case 2:
                try {
                conexion = DriverManager.getConnection(url, usuario, password);
                statement = conexion.createStatement();
                rs = statement.executeQuery("SELECT * FROM tbl_cliente");
                rs.next();
                do {
                    System.out.println(rs.getInt("idCliente") + " : " + rs.getString("Nombre") + " : " + rs.getString("Telefono"));
                } while (rs.next());

            } catch (SQLException ex) {
                Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
            MenuPrincipal.main(args);
            break;

            case 3:
                editarCliente update = new editarCliente();
                update.editarUsuario(args);
                break;

            case 4:
                
                eliminarCliente eliminarCx = new eliminarCliente();
                eliminarCx.eliminarUsuario(args);
                break;
                
            case 5:

                break;
            default:
                System.out.println("Ingrese una Opción Correcta");
                MenuPrincipal.main(args);
        }

    }

}
