// Crear una nueva tabla
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
// java.sql.Statement es una clase que tiene muchos métodos, pero hay dos que usaremos en 
// estos programas: executeUpdate() y executeQuery(). 
//
// executeUpdate() se usa para sentencias SQL que impliquen modificaciones en la base de datos 
// como INSERT, UPDATE, DELETE, etc. 
// Update significa actualización o modificación de algo que ya existe
//
// executeQuery() se usa sólo para consultas SELECT y similares.
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
            
            st.executeUpdate("CREATE TABLE usuarios (id INT AUTO_INCREMENT, PRIMARY KEY(id), nombre VARCHAR(50), correo VARCHAR(30),                               funcion VARCHAR(20))");

            // si se necesita borrar una tabla se usa el comando DROP TABLE igual que en SQL
            // st.executeUpdate("DROP TABLE usuarios");

            System.out.println("Tabla creada en la base de datos");

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
       