package pl.coderslab.web;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.dao.RecipeDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * Do not change servlet address !!!
 */
@WebServlet("")
public class HomeServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Test metody zliczającej ilość przepisów Admina:
//        AdminDao adminDao = new AdminDao();
//        RecipeDao recipeDao = new RecipeDao();
//        System.out.println("Ilość przepisów dodanych przez admina: " + recipeDao.recipesCount(adminDao.read(1)));

        getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);
    }
}
