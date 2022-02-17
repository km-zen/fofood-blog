package pl.coderslab.web;

import org.mindrot.jbcrypt.BCrypt;
import pl.coderslab.dao.AdminDao;
import pl.coderslab.dao.RecipeDao;
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

        if(admin!=null && checkPassword(password, admin.getPassword())){
            Cookie cookie = new Cookie("login", "true");
            RecipeDao recipeDao = new RecipeDao();
            HttpSession sess = request.getSession();
            sess.setAttribute("login", admin.getId());
            sess.setAttribute("recipesCount", recipeDao.recipesCount(admin));
//            cookie.setMaxAge(30 * 24 * 60 * 60);
//            response.addCookie(cookie);
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
