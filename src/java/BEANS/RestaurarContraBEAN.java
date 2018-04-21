/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BEANS;

import DAOS.EntidadPostgres;
import DAOS.UsuarioDAOS;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import otros.Security;

/**
 *
 * @author Julian Guerrero
 */
@ManagedBean
@SessionScoped
public class RestaurarContraBEAN {
String Email;
    String message;    
    private String passw;
    private String Usuario;
    FacesContext context;
HttpSession sessionUsuario;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
/**
     * Creates a new instance of RestaurarContraBEAN
     */
    public RestaurarContraBEAN() {
    }
    
    
     public String restaurarContr() throws SQLException {
       
       UsuarioDAOS udao = new UsuarioDAOS();
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession sessionUsuario = (HttpSession) context.getExternalContext().getSession(true);
        sessionUsuario.setAttribute("users", message);
        
        boolean op = udao.validarUsuario(message);
        

        if (op) {
            System.out.println("esta dando primera validacion");
            return "RestauraContra.xhtml?faces-redirect=true";
        } else {
            System.out.println("esta dando segunda validacion");
            return "CambioContra.xhtml?faces-redirect=true";
        }


    }     
//     para insertar los datos del registro del vhiculo en la BD 
    public void insertIntoBD(){
        
    } 
     public String fullDateUpdate() throws NoSuchAlgorithmException, SQLException{
//         UsuarioDAOS udao = new UsuarioDAOS();
        Security se = new Security();
        String claveEncriptada = se.setSHA512(passw);
//        udao.ActualizarDatosUsu(Usuario, claveEncriptada);
         EntidadPostgres ep=new EntidadPostgres();
         ep.actualizarQuery("UPDATE Login set pass='"+claveEncriptada+"' where users='"+Usuario+"'");
        return "index.xhtml?faces-redirect=true";
     }

    public String getPassw() {
        return passw;
    }

    public void setPassw(String passw) {
        this.passw = passw;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }
    
}
