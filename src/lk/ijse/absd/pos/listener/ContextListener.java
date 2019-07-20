package lk.ijse.absd.pos.listener;

import lk.ijse.absd.pos.db.DBConnection;

import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

@WebListener()
public class ContextListener implements ServletContextListener {

    private static ServletContext servletContext;
    // -------------------------------------------------------
    // ServletContextListener implementation
    // -------------------------------------------------------
    public void contextInitialized(ServletContextEvent sce) {
      /* This method is called when the servlet context is
         initialized(when the Web application is deployed). 
         You can initialize servlet context related data here.
      */
        servletContext = sce.getServletContext();
        try {
            DataSource connection = DBConnection.getConnection();
            servletContext.setAttribute("pool", connection);
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public void contextDestroyed(ServletContextEvent sce) {
      /* This method is invoked when the Servlet Context 
         (the Web application) is undeployed or 
         Application Server shuts down.
      */
        System.out.println("Destroyed!");
    }

    public static ServletContext getServletContext(){
        if(servletContext != null){
            return servletContext;
        }
        return null;
    }


}
