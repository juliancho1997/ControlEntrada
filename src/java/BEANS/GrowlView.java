/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BEANS;

import DAOS.UsuarioDAOS;
import java.sql.SQLException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean
@SessionScoped
public class GrowlView {

      FacesContext context;
    HttpSession sessionUsuario;
    
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void saveMessage() throws SQLException {

        UsuarioDAOS us = new UsuarioDAOS();
        FacesContext context = FacesContext.getCurrentInstance();
        boolean vali = us.validarUsuario(message);
        //context.addMessage(null, new FacesMessage("Successful",  "Your message: " + message) );
        if (vali) {
           // context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje info", "Si existe el usuario"));
            restaurarContr();
        } else {
            //context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje Error", "no existe el usuario"));
            restaurarContr();
        }
    }

 public String restaurarContr() throws SQLException {
       
       UsuarioDAOS udao = new UsuarioDAOS();

        context = FacesContext.getCurrentInstance();
        sessionUsuario = (HttpSession) context.getExternalContext().getSession(true);
        sessionUsuario.setAttribute("users", message);

        boolean op = udao.validarUsuario(message);

        if (op) {
            System.out.println("esta dando primera validacion");
            return "CambioContra.xhtml?faces-redirect=true";
        } else {
            System.out.println("esta dando segunda validacion");
            return "index.xhtml?faces-redirect=true";
        }


    }    
}
