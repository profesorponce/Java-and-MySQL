// Insertar datos en una tabla MySQL
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
// En este ejemplo, verá que los datos a insertar se ingresan en un array, pero el método de ingreso
// puede ser a través de un Frame con campos y cuando se presione el botón grabar. 
// El método de ingrearlos en un array es para facilitar medianamente el ingreso de datos semi-masivos.
// Observe que la sentencia INSERT se va componiendo a base de sumar cadenas de ordenes. 
// La sintaxis requerida por MySQL, igual que en SQL requiere que se ingresen comillas simples para 
// los valores de cadenas, (nombre, correo, funcion). La cadena INSERT se debería ver de este modo:
//
// INSERT INTO contacto (nombre, apellidos, telefono) VALUES ('Juan', 'Gomez', '123')
//
// En la linea de arriba se ven más claras las comillas simples para los datos que definimos como VARCHAR
// en nuestra tabla. Por eso en el código se puede ver que la formación de la cadena de órdenes para
// MySQL es del tipo ...VALUES ('"+nombres[i]+"','"...
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
            
            // preparamos los datos a ingresar en la tabla de la bdd
            String nombre[]={"Juan","Pedro","Antonio","Laura"};
            String correo[]={"juan@gmail.com","pedro@yahoo.com","antonio@live.com","laura@hotmail.com"};
            String funcion[]={"administracion","ventas","expediciones","despacho"};

            for (int i=0;i<nombre.length;i++)
                st.executeUpdate("INSERT INTO usuarios(nombre, correo, funcion) VALUES ('"+nombre[i]+"','"+correo[i]+"','"+funcion[i]+"' )");


            System.out.println("Datos ingresados a la tabla");

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
       