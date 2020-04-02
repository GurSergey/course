package com.company.servlet;

import com.company.db.PollsRepositoryDB;
import com.company.enitities.PollEntity;
import com.company.exceptions.SelectException;
import com.company.helpers.CookieHelper;
import com.company.services.PollsService;
import com.company.session.UserSessionStorage;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserAnswersServlet extends HttpServlet {
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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ServletContext context = this.getServletContext();
        int pollId = getPollId(request);
        if(pollId==NOT_FOUND_ID) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        } try {
            PollsService pollsService = new PollsService(new PollsRepositoryDB());
            String session = CookieHelper.getCookieByName(request,"userSession");
            PollEntity pollEntity = pollsService.getPollWithAnswersUser(pollId, UserSessionStorage.getUser(session).getId());
            context.setAttribute("poll", pollEntity);
        } catch (SelectException e) {

        }
        context.getRequestDispatcher("/user/answers.jsp").forward(request, response);
    }


}
