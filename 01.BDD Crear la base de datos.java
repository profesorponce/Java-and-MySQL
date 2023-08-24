// Crear una nueva base de datos
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
//           Nombre      tipo      tama�o  autoincremental
//           ----------  --------  ------  ---------------
// Campo 1 = id          int                    si 
// Campo 2 = Nombre      varchar   50           no 
// Campo 3 = Correo      varchar   30           no
// Campo 4 = Funcion     varchar   20           no
// -------------------------------------------------------

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PrimerPrograma{
    
      public static void main(String[] args){
  
          // Intento de conexi�n a una base de datos
          String servidor = "localhost:3306";
        
          // definici�n de datos que necesitaremos para establecer la conexi�n
          String usuario = "root";  //nombre del usuario con acceso
          String clave = ""; //contrase�a del usuario con acceso
          String url = "jdbc:mysql://" + servidor + "/" +
                       "?serverTimezone=America/Argentina/Buenos_Aires";
        
          // se declara una conexion del clase java.sql.Connection
          java.sql.Connection cnx;
                
          try {
            
              // se establece la conexi�n con MySQL
              cnx = java.sql.DriverManager.getConnection(url, usuario, clave);
              System.out.println("Conexi�n Establecida");
            
              //nombre de la base de datos que vamos a crear
              String baseDatos = "javabdd"; 

              // creamos una secuencia de comandos de comunicaci�n para enviar ordenes
              java.sql.Statement st = cnx.createStatement();
                        
	      // enviamos la orden de creaci�n de una nueva base de datos 
              st.executeUpdate("CREATE DATABASE "+baseDatos);

              // resultado de la operaci�n
              System.out.println("Nueva Base de Datos Creada");

              st.close();
              cnx.close();
            
          }catch (java.sql.SQLException ex){

            System.out.println("Conexi�n ERROR");
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            
          } //fin try-catch         
                
          
      } // fin de main
       
} // fin de la clase principal
       