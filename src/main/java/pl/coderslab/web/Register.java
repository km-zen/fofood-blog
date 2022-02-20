package pl.coderslab.web;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.dao.PlanDao;
import pl.coderslab.model.Admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Map;

@WebServlet("/register")
public class Register extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getServletContext().getRequestDispatcher("/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        //        test pobieranych wartości:
        Map<String, String[]> parameterMap = request.getParameterMap();
        for (String key : parameterMap.keySet()) {

            response.getWriter().println("key: " + key + "<br>");
            for (String value : parameterMap.get(key)) {
                response.getWriter().println("value " + value+ "<br>");

            }
        }


        //        koniec testu pobieranych wartości:

        //nowy admin
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Admin admin = new Admin();
        admin.setFirstName(name);
        admin.setLastName(surname);
        admin.setEmail(email);
        admin.setPassword(password);
        AdminDao adminDao = new AdminDao();
        adminDao.create(admin);



        response.sendRedirect(request.getContextPath() + "/login");


    }
}
