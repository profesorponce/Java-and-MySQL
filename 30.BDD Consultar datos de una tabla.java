// Consultar datos de una tabla MySQL
//
// (c) Ricardo Ponce
// https://www.profesorponce.blogspot.com
// Agosto 2022
// 
// Base de datos
// -------------------------------------------------------
// Nombre = JavaBDD
// Usuario = root (password =no)
// Usario =  ricardo (password=123456)
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
// Las consultas de datos se hacen con el método executeQuery() que nos devuelve ResultSet.
// El ResultSet es una conexión activa hacia los datos que nos permite recibirlos a medida que los pidamos
// Es por eso que si una consulta devuelve muchos resultados, no se nos va a agotar la memoria
// Para traer el primer resultado, debemos llamar el método next() del ResulSet. 
// Para el siguiente dato usamos otro next() y así sucesivamente hasta que next() devuelva un resultado false
// indicando que llegamos al final de la consulta
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
            ResultSet rs = st.executeQuery("SELECT * FROM usuarios"); 
            
            // generamos un bucle de consulta que trae datos 
            // mientras no lleguemos al final de la tabla
            
            int cantidad = 0;
            
            while (rs.next()) // mientras next indique que hay datos
            {
                System.out.println("nombre="+rs.getObject("nombre")+
                ", correo="+rs.getObject("correo")+
                ", funcion="+rs.getObject("funcion"));
                cantidad = cantidad +1;
            }

            // La forma de lee los datos de los campos es pedirlos con algún método get(). 
            // Si usted sabe de qué tipo es el dato, puede pedirlo con getInt(), getString()
            // Si no lo sabes puede usar un getObject(), que es capaz de traer cualquier tipo de dato

            rs.close(); // cuando no hay mas datos cierra la consulta
            System.out.println("---------------------");
            System.out.println(String.valueOf(cantidad)+" registros.");
            System.out.println("---------------------");
            System.out.println("Consulta de datos de tabla terminada");

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
       