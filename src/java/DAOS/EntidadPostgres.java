/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOS;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 *
 * @author secar-
 */
public class EntidadPostgres {
     private String usuario ="postgres" ;//nombre del usuario de la base de datos    
    private String contraseña = "manager";//contraseÃ±a del usuario en la BD
    protected Connection conexion;//es protegido para poder usarlo desde la clase que hereda
    private Statement sentenciaSQL;
    private ResultSet resultSet;
    PreparedStatement stmt = null;
    //parametros para acceder a la BD
    private String indice;
    private String tabla;
    static private ResultSet resultado = null;
    /***************************************************************************/
    public EntidadPostgres() {//constructor
        try {
            //cargar el controlador de la JBDC
            String controlador = "org.postgresql.Driver";
            Class.forName(controlador).newInstance();
            conectar();
        } catch (InstantiationException ex) {
            Logger.getLogger(EntidadPostgres.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(EntidadPostgres.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EntidadPostgres.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(EntidadPostgres.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
/***************************************************************************/
    private void conectar() throws SQLException {        
//        String url = "jdbc:postgresql://localhost:5432/denkitronik";
        //url a la que me voy a conectar
        String url = "jdbc:postgresql://127.0.0.1:5432/ControlEntradaBD";
        //instacio la conexion para usar la url, el usuario, y la contraseÃ±a
        conexion = (Connection) DriverManager.getConnection(url, usuario, contraseña);
        // Crear una sentencia SQL
        sentenciaSQL = (Statement) conexion.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        System.out.println("\nConexion realizada con exito.\n");
    }
/***************************************************************************/
    public ResultSet ejecutarQuery(String sql) {
        ResultSet resultado = null;
        try {
            resultado = sentenciaSQL.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(EntidadPostgres.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }

  /***************************************************************************/
    public int actualizarQuery(String sql) {
        int resultado = 0;
        try {
            resultado = sentenciaSQL.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(EntidadPostgres.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
             cerrarConexion();
        }
        return resultado;
    }

/***************************************************************************/
    public void cerrarConexion() {
        try {
            
            if (resultSet != null) {
                resultSet.close();
            }
            if (sentenciaSQL != null) {
                sentenciaSQL.close();
            }
            if (conexion != null) {
                conexion.close();
                System.out.println("cerro conexion");
            }
        } catch (SQLException ex) {
            Logger.getLogger(EntidadPostgres.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  

///***************************************************************************/ 
//  public static void main(String david[]){
//      EntidadPostgres ep=new EntidadPostgres();
//      
//  }
}
