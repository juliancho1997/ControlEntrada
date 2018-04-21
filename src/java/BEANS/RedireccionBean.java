/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BEANS;


import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

/**
 *
 * @author Julian Guerrero
 */
@ManagedBean
@SessionScoped
public class RedireccionBean {

    /**
     * Creates a new instance of RedireccionBean
     */
    public RedireccionBean() {
    }

    public void isAdmin(ComponentSystemEvent event) {

        FacesContext fc = FacesContext.getCurrentInstance();

        if (!"julian".equals(fc.getExternalContext().getSessionMap().get("Admin"))) {
            ConfigurableNavigationHandler nav
                    = (ConfigurableNavigationHandler) fc.getApplication().getNavigationHandler();

            nav.performNavigation("registroVehiculo.xhtml");
        }

    }
}
