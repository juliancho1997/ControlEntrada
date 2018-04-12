/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BEANS;

import DAOS.UsuarioDAOS;
import otros.Security;
import javax.faces.application.FacesMessage;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

//annontations
@ManagedBean
@SessionScoped
public class LoginBean {

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
   
    private String usuario;
    private String clave;
    private String menssage;
    private String email;

    FacesContext context;
    HttpSession sessionUsuario;

    public LoginBean() {
        
    }
             
 public String validarUEm () throws SQLException{
         UsuarioDAOS udao =  new UsuarioDAOS();
         
         boolean op = udao.validarEmail(usuario,email);
         
         if (op)
         {
             context = FacesContext.getCurrentInstance();
             sessionUsuario = (HttpSession) context.getExternalContext().getSession(true);
             sessionUsuario .setAttribute("email",email);
             
             return "RestauraContra.xhtml?faces-redirect=true";
         }
         else
         {
             return "CambioContra.xhtml?faces-redirect=true";
         }
         
     }
   

    public String loguearse() throws SQLException, NoSuchAlgorithmException {
        UsuarioDAOS udao = new UsuarioDAOS();
        Security se = new Security();
        String claveEncriptada = se.setSHA512(clave);
        boolean op = udao.validarDatos(usuario, claveEncriptada);
        if (op) {
            //creo una session de usuario
            context = FacesContext.getCurrentInstance();
            sessionUsuario = (HttpSession) context.getExternalContext().getSession(true);
            sessionUsuario.setAttribute("users", usuario);

            return "welcomePrimefaces.xhtml?faces-redirect=true";

        } else {
            return "index.xhtml?faces-redirect=true";
        }
    }

    public String restaurarContr() throws SQLException {
       UsuarioDAOS udao = new UsuarioDAOS();
       
        boolean op = udao.validarUsuario(usuario);
        if (op) {
            //creo una session de usuario
            context = FacesContext.getCurrentInstance();
            sessionUsuario = (HttpSession) context.getExternalContext().getSession(true);
            sessionUsuario.setAttribute("users", usuario);

            return "RestauraContra.xhtml?faces-redirect=true";

        } else {
            return "index.xhtml?faces-redirect=true";
        }


    }

    public String cerrarSession() {

        sessionUsuario.invalidate();
        return "index.xhtml?faces-redirect=true";
    }

    public String olvidoContra() throws SQLException {
        UsuarioDAOS udao = new UsuarioDAOS();

        context = FacesContext.getCurrentInstance();
        sessionUsuario = (HttpSession) context.getExternalContext().getSession(true);
        sessionUsuario.setAttribute("users", usuario);

        boolean op = udao.validarUsuario(usuario);

        if (op) {
            return "CambioContra.xhtml?faces-redirect=true";
        } else {
            return "index.xhtml?faces-redirect=true";
        }

    }
    FacesContext facesContext;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getMenssage() {
        return menssage;
    }

    public void setMenssage(String menssage) {
        this.menssage = menssage;
    }

}
