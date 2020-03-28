package com.company.servlet;

import com.company.db.UserRepositoryDB;
import com.company.enums.EntityError;
import com.company.exceptions.SelectException;
import com.company.services.UserService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminListOfUserServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IOException {
        ServletContext context = getServletContext();
        UserService service = new UserService(new UserRepositoryDB());
        try {

            context.setAttribute("users", service.getAllUsers());
        } catch (SelectException e) {
            context.setAttribute("error", EntityError.SELECT);
        }
        context.getRequestDispatcher("/admin/user_list.jsp").forward(request, response);
    }

}
