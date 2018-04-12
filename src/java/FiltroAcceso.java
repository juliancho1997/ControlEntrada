/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "FiltroAcceso", urlPatterns = {"/.xhtml"})
public class FiltroAcceso implements Filter {

    private static final boolean debug = true;
    private FilterConfig filterConfig = null;

    public FiltroAcceso() {}

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        try {
            HttpServletRequest request = (HttpServletRequest) req;
            HttpServletResponse response = (HttpServletResponse) resp;
            HttpSession session = request.getSession(false);
            String reqURI = request.getRequestURI();
            if (reqURI.indexOf("/index.xhtml") >= 0 || (session != null && session.getAttribute("users") != null) || reqURI.indexOf("/public/") >= 0 || reqURI.contains("javax.faces.resource")) {
                System.out.println("5.FiltroAcceso - El usuario esta logueado");
                chain.doFilter(req, resp);
            } else {
                System.out.println("5.FiltroAcceso - El usuario NO esta logueado");
                response.sendRedirect(request.getContextPath() + "/faces/index.xhtml");
            }
        } catch (Throwable t) {
            System.out.println(t.getMessage());
        }
    }

    @Override
    public void destroy() {
    
    }

}

