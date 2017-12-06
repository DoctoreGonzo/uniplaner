/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 *
 * @author 150237mg
 */
@WebListener
public class DBListener implements ServletContextListener{

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        DBConnectionPool dbPool = DBConnectionPool.createConnectionPool();
        ServletContext sc = sce.getServletContext();
        sc.setAttribute("dbPool", dbPool);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        DBConnectionPool dbPool = (DBConnectionPool) sce.getServletContext().getAttribute("dbPool");
        dbPool.closeConnections();
    }
    
}
