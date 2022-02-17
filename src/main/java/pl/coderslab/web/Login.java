package pl.coderslab.web;

import org.mindrot.jbcrypt.BCrypt;
import pl.coderslab.dao.AdminDao;
import pl.coderslab.model.Admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/login")
public class Login extends HttpServlet {
    AdminDao adminDao = new AdminDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Admin admin = adminDao.findByEmail(email);

        Boolean ifValid = false;
        if(admin!=null && email!=null){
            if(checkPassword(password, admin.getPassword()) && email.equals(admin.getEmail())){
                ifValid = true;
            }
        }
        if(ifValid){
            response.sendRedirect("/");
        } else {
            request.setAttribute("incorrectLoginData", "true");
            doGet(request, response);
        }
    }

    public boolean checkPassword(String password, String hasched) {
        return BCrypt.checkpw(password, hasched);
    }

}
