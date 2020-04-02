package com.company.servlet;

import com.company.db.UserRepositoryDB;
import com.company.enitities.Entity;
import com.company.enitities.VoterEntity;
import com.company.enums.EntityError;
import com.company.exceptions.SelectException;
import com.company.exceptions.UpdateException;
import com.company.filters.UserSessionFilter;
import com.company.helpers.CookieHelper;
import com.company.services.UserService;
import com.company.session.UserSessionStorage;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserEditProfileServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
        ServletContext context = getServletContext();
        UserService service = new UserService(new UserRepositoryDB());
        try {
            String session = CookieHelper.getCookieByName(request,"userSession");
            VoterEntity voter = service.getVoterById(UserSessionStorage.getUser(session).getId());
            request.setAttribute("voter", voter);
            request.setAttribute("error", EntityError.NO_ERROR_UPDATE);
        } catch ( SelectException e) {
            request.setAttribute("error", EntityError.SELECT);
        }
        context.getRequestDispatcher("/user/edit_profile.jsp").forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext context = getServletContext();
        UserService service = new UserService(new UserRepositoryDB());
        VoterEntity voter = new VoterEntity();
        voter.setId(Integer.parseInt(request.getParameter("id")));
        voter.setName(request.getParameter("name"));
        voter.setPassword(request.getParameter("password"));
        voter.setPhone(request.getParameter("phone"));
        try {
            service.updateVoter(voter);
            request.setAttribute("error", EntityError.NO_ERROR_UPDATE);
        } catch (UpdateException e) {
            request.setAttribute("error", EntityError.UPDATE);
        }
        doGet(request,response);
//        context.getRequestDispatcher("/user/edit_profile.jsp").forward(request, response);

    }
}
