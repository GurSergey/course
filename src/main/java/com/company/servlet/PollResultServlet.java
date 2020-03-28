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

public class PollResultServlet extends HttpServlet {
    public PollResultServlet(){
        super();
    }

    private static final int NOT_FOUND_ID = -1;

    private int getPollId(HttpServletRequest request){
        int pollId;
        if(!request.getParameterMap().containsKey("pollId")) {
            pollId = NOT_FOUND_ID;
        } else {
            pollId = Integer.parseInt(request.getParameter("pollId"));
        }
        return pollId;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PollsService pollsService = new PollsService(new PollsRepositoryDB());
        ServletContext context = this.getServletContext();
        try {
            PollEntity poll =  pollsService.getPollResult(getPollId(request));
            context.setAttribute("poll", poll);
        } catch (SelectException e) {
            context.setAttribute("error", EntityError.SELECT);
        }
        context.getRequestDispatcher("/result_poll.jsp").forward(request, response);
    }
}
