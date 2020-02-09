package org.itstep.Servlet;

import org.itstep.db.ToDoDAO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "delete",urlPatterns = "/delete",loadOnStartup = 1)
public class DeletePostServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-config.xml");
        ToDoDAO bean = applicationContext.getBean(ToDoDAO.class);
        bean.remove(id);

        resp.sendRedirect(getServletContext().getContextPath() + "/");
    }
}
