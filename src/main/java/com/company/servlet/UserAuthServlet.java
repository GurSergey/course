package com.company.servlet;

import com.company.db.UserRepositoryDB;
import com.company.enitities.VoterEntity;
import com.company.exceptions.SelectException;
import com.company.helpers.CookieHelper;
import com.company.services.UserService;
import com.company.session.AdminSessionStorage;
import com.company.session.UserSessionStorage;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class UserAuthServlet extends HttpServlet {
    public UserAuthServlet(){
        super();
    }

    private static final int SIZE_SESSION_ID = 100;

    private String generateSessionId() {
        byte[] array = new byte[SIZE_SESSION_ID]; // length is bounded by 7
        new Random().nextBytes(array);
        return new String(array, StandardCharsets.UTF_8);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        boolean authPassed = false;
        String session = CookieHelper.getCookieByName(request,"userSession");
        if(session!= null && UserSessionStorage.getIdUser(session)!=null)
            authPassed = true;
        ServletContext context = getServletContext();
        context.setAttribute("authPassed", authPassed);
        context.getRequestDispatcher("/user/auth.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        String password = request.getParameter("password");
        String login = request.getParameter("login");
        UserService service = new UserService(new UserRepositoryDB());
        ServletContext context = getServletContext();
        try {
            VoterEntity voter = service.getUserByLoginPassword(login, password);
            if(voter != null){
                String sessionId = generateSessionId();
                CookieHelper.setCookieByName(response, "userSession", sessionId);
                UserSessionStorage.setSession(sessionId, voter.getId());
            }
        } catch (SelectException e) {
            e.printStackTrace();
        }
        context.getRequestDispatcher("/user/menu.jsp").forward(request, response);
    }
}
