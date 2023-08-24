// Modificar datos de una tabla MySQL
//
// (c) Ricardo Ponce
// https://www.profesorponce.blogspot.com
// Agosto 2022
// 
// Base de datos
// -------------------------------------------------------
// Nombre = JavaBDD
// Usuario = root (password =no)
// 
// Tabla   = usuarios
// 
//           Nombre      tipo      tamaño  autoincremental
//           ----------  --------  ------  ---------------
// Campo 1 = id          int                    si 
// Campo 2 = Nombre      varchar   50           no 
// Campo 3 = Correo      varchar   30           no
// Campo 4 = Funcion     varchar   20           no
// -------------------------------------------------------
// 
// Para actualizar un registro usamos executeUpdate(). En este ejemplo vamos a cambiar 
// el correo electrónico de Laura 
// En la tabla hemos definido un campo id (que es indice), es por eso que primero vamos
// a obtener el id de Laura para luego usarlo en el UPDATE y modificar su correo electrónico
//
// Lo haremos mediante:
// 
// rs = st.executeQuery("SELECT id FROM usuarios WHERE nombre='Laura'");
// rs.next();
// int id = rs.getInt("id");
// 
// La orden SELECT estructurada así permite obtener el campo id de Laura. 
// Con rs.next() haremos que se pueda obtener el resultado de la consulta que individualiza el
// registro de Laura. Como sabemos que es un INT, lo obtenemos mediante getInt() y lo guardamos.
// Tenga en cuenta que pueden haber más de una Laura en la tabla, por lo que debería ver si 
// rs.next() devuelve o no otro resultado y seleccionar de entre ellos cuál registro debe ser modificado.
//

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PrimerPrograma{
    
      public static void main(String[] args){
  
        // Intento de conexión a una base de datos
        String servidor = "localhost:3306";
        String baseDatos = "javabdd"; //nombre de la base de datos
        String usuario = "root";  //nombre del usuario con acceso
        String clave = ""; //contraseña
        String url = "jdbc:mysql://" + servidor + "/" +baseDatos+
                     "?serverTimezone=America/Argentina/Buenos_Aires";
        
        // se declara una conexion del clase java.sql.Connection
        java.sql.Connection cnx;
        
        try {
            
            cnx = java.sql.DriverManager.getConnection(url, usuario, clave);
            System.out.println("Conexión Establecida");
            
            // creamos una secuencia de comandos para enviar ordenes a la bdd
            java.sql.Statement st = cnx.createStatement();
            
            // creamos una secuencia de consulta para nuestra tabla
            ResultSet rs = st.executeQuery("SELECT id FROM usuarios WHERE nombre='Laura'"); 
                                              
            rs.next(); // se posiciona en el registro
            System.out.println("Registro Id="+rs.getInt("id"));

            // obtenemos el id o indice de la tabla que contiene el registro buscado
            int id = rs.getInt("id");
                
            st.executeUpdate("UPDATE usuarios SET correo='nuevocorreo@gmail.com' WHERE id="+id);
                           
            System.out.println("Modificado el registro");

            st.close();
            cnx.close();
            
        }catch (java.sql.SQLException ex){

            System.out.println("Conexión ERROR");
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            
        } //fin try-catch         
                      
          

          
      } // fin de main
       
} // fin de la clase principal
       