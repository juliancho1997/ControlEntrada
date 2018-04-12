/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOS;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author secar
 */
public class UsuarioDAOS extends EntidadPostgres {

    public UsuarioDAOS() {
    }

    public boolean validarDatos(String usuario, String clave) throws SQLException {
        ResultSet resultado = null;
        boolean valido = false;
        String users = "";
        String password = "";
        try {
            //declaro a sentensia sql que quiero ejecutar
            stmt = conexion.prepareStatement("SELECT * FROM Login where users=? and pass=?",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            stmt.setString(1, usuario);
            stmt.setString(2, clave);
            //asigno resultado devuelto por la DB
            resultado = stmt.executeQuery();
        } catch (Exception ex) {
            System.out.println("Error durante la consulta");
        }
        try {
            while (resultado.next()) {
                users = resultado.getString("users");
                password = resultado.getString("pass");
            }
            if (users.equals(usuario) && password.equals(clave)) {
                valido = true;
            } else {
                valido = false;
            }
        } catch (Exception e) {
            System.out.println("Error "+e);
        }
        finally{
            cerrarConexion();
        }

        return valido;
    }

    public boolean validarUsuario(String usuario) throws SQLException {
        ResultSet resultado = null;
        boolean valido = false;
        String users = "";

        try {
            //declaro a sentensia sql que quiero ejecutar
            stmt = conexion.prepareStatement("SELECT * FROM Login where users=? ",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            stmt.setString(1, usuario);

            //asigno resultado devuelto por la DB
            resultado = stmt.executeQuery();
        } catch (Exception ex) {
            System.out.println("Error durante la consulta");
        }
        try {
            while (resultado.next()) {
                users = resultado.getString("users");
            }
            if (users.equals(usuario)) {
                valido = true;
            } else {
                valido = false;
            }
        } catch (Exception error) {
            System.out.println("Error por esto " + error);
        } finally {
            cerrarConexion();
        }
        return valido;
    }
     public void ActualizarDatosUsu(String usuario, String clave) throws SQLException {
        ResultSet resultado = null;
        boolean valido = false;
        String users = "";
        String password="";

        try {
            //declaro a sentensia sql que quiero ejecutar
            stmt = conexion.prepareStatement("UPDATE Login where users=? set pass=?",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            System.out.println("si esta entrando aqui");
            stmt.setString(1, usuario);
            stmt.setString(2, clave);
            System.out.println("si esta tomando los valores");
            //asigno resultado devuelto por la DB
            resultado = stmt.executeQuery();
        } catch (Exception ex) {
            System.out.println("Error durante la actualizar");
        }
        
         finally {
            cerrarConexion();
        }
        
    }
    public boolean validarEmail(String usuario, String email) throws SQLException {
        ResultSet resultado = null;
        boolean valido = false;
        String users = "";
        String mail = "";

        try {
            //declaro a sentensia sql que quiero ejecutar
            stmt = conexion.prepareStatement("SELECT * FROM Login where users=? set email=? ",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            stmt.setString(1, usuario);
            stmt.setString(2, email);

            //asigno resultado devuelto por la DB
            resultado = stmt.executeQuery();
        } catch (Exception ex) {
            System.out.println("Error durante la consulta");
        }
        try {
            while (resultado.next()) {
                users = resultado.getString("users");
                mail = resultado.getString("email");
            }
            if (users.equals(usuario) && mail.equals(email)) {
                valido = true;
            } else {
                valido = false;
            }
        } catch (Exception error) {
            System.out.println("Error por esto " + error);
        } finally {
            cerrarConexion();
        }
        return valido;
    }
        
    }


