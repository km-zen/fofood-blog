package pl.coderslab.web;

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

                    //        test pobieranych wartości:
        Map<String, String[]> parameterMap = request.getParameterMap();
        for (String key : parameterMap.keySet()) {
            response.getWriter().println("key: " + key);
            for (String value : parameterMap.get(key)) {
                response.getWriter().println("value" + value);
            }
        }
                    //        koniec testu pobieranych wartości:

    }
}
