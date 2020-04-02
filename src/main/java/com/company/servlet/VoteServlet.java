package com.company.servlet;

import com.company.db.AnswerRepositoryDB;
import com.company.db.QuestionsRepositoryDB;
import com.company.db.VariantsRepositoryDB;
import com.company.enitities.AnswerEntity;
import com.company.enums.EntityError;
import com.company.exceptions.InsertException;
import com.company.exceptions.SelectException;
import com.company.helpers.CookieHelper;
import com.company.services.AnswerService;
import com.company.services.QuestionsService;
import com.company.services.VariantsService;
import com.company.session.UserSessionStorage;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

public class VoteServlet extends HttpServlet {

    public VoteServlet(){super();}

    private static final int NOT_FOUND_ID = -1;

    private int getPollId(HttpServletRequest request){
        int questionId;
        if(!request.getParameterMap().containsKey("pollId")) {
            questionId = NOT_FOUND_ID;
        } else {
            questionId = Integer.parseInt(request.getParameter("pollId"));
        }
        return questionId;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext context = this.getServletContext();
        int pollId = getPollId(request);
        if(pollId == NOT_FOUND_ID) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        try {
            QuestionsService service = new QuestionsService(new QuestionsRepositoryDB());
            String session = CookieHelper.getCookieByName(request,"userSession");
            context.setAttribute("question", service.getQuestionForUser(pollId, UserSessionStorage.getUser(session).getId()));
        } catch (SelectException e) {
            context.setAttribute("error", EntityError.SELECT);
        }
        getServletContext().getRequestDispatcher("/user/vote.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        ServletContext context = this.getServletContext();
        try {
            AnswerService service = new AnswerService(new AnswerRepositoryDB());
            AnswerEntity answer = new AnswerEntity();
            answer.setAnswerDate((new Date((new java.util.Date()).getTime())));
            String session = CookieHelper.getCookieByName(request,"userSession");
            answer.setVoterId(UserSessionStorage.getUser(session).getId());
            answer.setVariantId(Integer.parseInt(request.getParameter("variantId")));

            service.saveAnswer(answer);
        } catch (InsertException e) {
            context.setAttribute("error", EntityError.INSERT);
        }
        this.doGet(request, response);
    }

}
