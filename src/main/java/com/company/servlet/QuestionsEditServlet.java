package com.company.servlet;

import com.company.db.QuestionsRepositoryDB;
import com.company.enitities.PollEntity;
import com.company.enitities.QuestionEntity;
import com.company.enums.EntityError;
import com.company.exceptions.DeleteException;
import com.company.exceptions.InsertException;
import com.company.exceptions.SelectException;
import com.company.exceptions.UpdateException;
import com.company.services.QuestionsService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

public class QuestionsEditServlet extends HttpServlet {
    public QuestionsEditServlet(){
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
        ServletContext context = this.getServletContext();
        int pollId = getPollId(request);
        if(pollId==NOT_FOUND_ID) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        try {
            QuestionsService service = new QuestionsService(new QuestionsRepositoryDB());
            request.setAttribute("pollId", request.getParameter("pollId"));
            request.setAttribute("questions", service.getQuestionsByIdPoll(pollId));
        } catch (SelectException e) {
            request.setAttribute("error", EntityError.SELECT);
        }
        getServletContext().getRequestDispatcher("/admin/questions.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int pollId = getPollId(request);
        if(pollId==NOT_FOUND_ID){
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.setId(request.getParameterMap().containsKey("id") ? Integer.parseInt(request.getParameter("id"))
                : -1);
        questionEntity.setQuestion( request.getParameter("question"));
        questionEntity.setPollId( Integer.parseInt(request.getParameter("pollId")));
        questionEntity.setCreatedDate(new Date((new java.util.Date()).getTime()));
        ServletContext context = this.getServletContext();
        try {
            QuestionsService service = new QuestionsService(new QuestionsRepositoryDB());

            if(request.getParameterMap().containsKey("save")){
                service.saveQuestion(questionEntity);
                request.setAttribute("error", EntityError.NO_ERROR_INSERT);
            }
            if(request.getParameterMap().containsKey( "update")){
                service.updateQuestion(questionEntity);
                request.setAttribute("error", EntityError.NO_ERROR_UPDATE);
            }
            if(request.getParameterMap().containsKey("delete")){
                service.deleteQuestion(questionEntity);
                request.setAttribute("error", EntityError.NO_ERROR_DELETE);
            }
            request.setAttribute("questions", service.getQuestionsByIdPoll( pollId));
            request.setAttribute("pollId", request.getParameter("pollId"));
        } catch (SelectException e){
            request.setAttribute("error", EntityError.SELECT);
        } catch (InsertException e){
            request.setAttribute("error", EntityError.INSERT);
        } catch (UpdateException e) {
            request.setAttribute("error", EntityError.UPDATE);
        } catch (DeleteException e) {
            request.setAttribute("error", EntityError.DELETE);
        }
        getServletContext().getRequestDispatcher("/admin/questions.jsp").forward(request, response);
    }
}
