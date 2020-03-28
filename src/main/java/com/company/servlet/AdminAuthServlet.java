package com.company.servlet;

import com.company.enums.Roles;
import com.company.helpers.CookieHelper;
import com.company.session.AdminSessionStorage;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class AdminAuthServlet extends HttpServlet {

    private static final int SIZE_SESSION_ID = 100;
    private static final String LOGIN_ADMIN = "admin";
    private static final String PASS_ADMIN = "123";

    private String generateSessionId() {
        byte[] array = new byte[SIZE_SESSION_ID]; // length is bounded by 7
        new Random().nextBytes(array);
        return new String(array, StandardCharsets.UTF_8);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        boolean authPassed = false;
        String session = CookieHelper.getCookieByName(request,"adminSession");
        if(session!= null && AdminSessionStorage.sessionIsActive(session))
            authPassed = true;
        ServletContext context = getServletContext();
        context.setAttribute("authPassed", authPassed);
        context.getRequestDispatcher("/admin/auth.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        String password = request.getParameter("password");
        String login = request.getParameter("login");
        if(password.equals(PASS_ADMIN) && login.equals(LOGIN_ADMIN)){
            String sessionId = generateSessionId();
            CookieHelper.setCookieByName(response, "adminSession", sessionId);
            AdminSessionStorage.setSession(sessionId);
        }
        ServletContext context = getServletContext();
        context.getRequestDispatcher("/admin/menu.jsp").forward(request, response);
    }
}
