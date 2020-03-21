package com.company.servlet;

import com.company.db.PollsRepositoryDB;
import com.company.enitities.PollEntity;
import com.company.services.PollsService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PollEditServlet extends HttpServlet {
    public PollEditServlet(){
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PollsService service = new PollsService(new PollsRepositoryDB());
        request.setAttribute("polls", service.getPolls());
        request.getRequestDispatcher("polls.jsp").forward(request, response);

//        ServletContext servletContext = getServletContext();
//        servletContext.setAttribute("name", "Tom");
//        servletContext.setAttribute("age", 35);
        //servletContext.getRequestDispatcher("index.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
