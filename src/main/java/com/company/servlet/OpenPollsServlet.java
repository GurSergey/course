package com.company.servlet;

import com.company.db.PollsRepositoryDB;
import com.company.enitities.PollEntity;
import com.company.enums.EntityError;
import com.company.exceptions.SelectException;
import com.company.services.PollsService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OpenPollsServlet extends HttpServlet {
    public OpenPollsServlet(){
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext context = getServletContext();
        PollsService service = new PollsService(new PollsRepositoryDB());
        try {
            PollEntity[] pollEntities = service.getAllOpenPolls();
            context.setAttribute("polls", pollEntities);
        } catch (SelectException e) {
            context.setAttribute("error", EntityError.SELECT);
        }
        context.getRequestDispatcher("/open_polls.jsp").forward(request, response);

    }
}
