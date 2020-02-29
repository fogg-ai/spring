package org.itstep;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyContextServletListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Application started");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Application destroyed");
    }
}
