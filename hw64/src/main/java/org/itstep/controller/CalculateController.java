package org.itstep.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class CalculateController {

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String index(HttpServletRequest req, HttpServletResponse resp) {
        String a = req.getParameter("a");
        String b = req.getParameter("b");
        String op = req.getParameter("op");

        double res = 0;
        if (a != null && b != null && op != null) {
            if (op.equals(" ")) {
                op = "+";
                res = Double.valueOf(a) + Double.valueOf(b);
            } else if (op.equals("-")) {
                res = Double.valueOf(a) - Double.valueOf(b);
            } else if (op.equals("*")) {
                res = Double.valueOf(a) * Double.valueOf(b);
            } else if (op.equals("/")) {
                res = Double.valueOf(a) / Double.valueOf(b);
            }
        }
        String ex = a + " " + op + " " + b + " = " + res;

        req.setAttribute("example", ex);

        return "index";
    }
}
