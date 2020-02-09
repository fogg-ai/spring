package org.itstep.Servlet;

import org.itstep.ToDo;
import org.itstep.db.ToDoDAO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "add",urlPatterns = "/add",loadOnStartup = 1)
public class AddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/todoadd.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-config.xml");
        String shortDescription = req.getParameter("shortDescription");
        String longDescription = req.getParameter("longDescription");
        String categoryName = req.getParameter("categoryName");
        String start = req.getParameter("start");
        String end = req.getParameter("end");

        ToDoDAO bean = applicationContext.getBean(ToDoDAO.class);
        ToDo toDo = new ToDo();
        if(!"".equals(shortDescription)){
            toDo.setShortDescription(shortDescription);
        }
        if(!"".equals(longDescription)){
            toDo.setLongDescription(longDescription);
        }
        if(!"".equals(start)) {
            toDo.setStart(start);
        }
        if(!"".equals(end)){
            toDo.setEnd(end);
        }
        if(!"".equals(categoryName)){
            toDo.setCategoryName(categoryName);
        }else {
            toDo.setCategoryName("");
        }

        bean.add(toDo);

        resp.sendRedirect(getServletContext().getContextPath() + "/add");
    }
}
