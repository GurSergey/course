package com.company.servlet;

import com.company.db.UserRepositoryDB;
import com.company.enitities.VoterEntity;
import com.company.enums.EntityError;
import com.company.exceptions.InsertException;
import com.company.services.UserService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

public class UserRegistrationServlet extends HttpServlet {
    public UserRegistrationServlet(){
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext context = getServletContext();
        context.getRequestDispatcher("/user/registration.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext context = getServletContext();
        UserService service = new UserService(new UserRepositoryDB());
        VoterEntity voter = new VoterEntity();
        voter.setLogin(request.getParameter("login"));
        voter.setName(request.getParameter("name"));
        voter.setPassword(request.getParameter("password"));
        voter.setPhone(request.getParameter("phone"));
        voter.setRegistrationDate(new Date((new java.util.Date()).getTime()));
        try {
            service.createUser(voter);
            response.sendRedirect(request.getContextPath()+"/user_auth");
//            context.getRequestDispatcher("/user_auth.jsp").forward(request, response);
        } catch (InsertException e) {
            request.setAttribute("error", EntityError.INSERT);
            context.getRequestDispatcher("/user/registration.jsp").forward(request, response);
        }

    }
}
