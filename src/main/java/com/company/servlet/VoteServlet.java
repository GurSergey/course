package com.company.servlet;

import com.company.db.VariantsRepositoryDB;
import com.company.enums.EntityError;
import com.company.exceptions.SelectException;
import com.company.services.VariantsService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class VoteServlet extends HttpServlet {

    public VoteServlet(){super();}

    private static final int NOT_FOUND_ID = -1;

    private int getPollId(HttpServletRequest request){
        int questionId;
        if(!request.getParameterMap().containsKey("questionId")) {
            questionId = NOT_FOUND_ID;
        } else {
            questionId = Integer.parseInt(request.getParameter("questionId"));
        }
        return questionId;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext context = this.getServletContext();
        int questionId = getPollId(request);
        if(questionId == NOT_FOUND_ID) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        try {
            VariantsService service = new VariantsService(new VariantsRepositoryDB());
            context.setAttribute("variants", service.getVariantsByIdQuestion(questionId));
        } catch (SelectException e) {
            context.setAttribute("error", EntityError.SELECT);
        }
        getServletContext().getRequestDispatcher("/user/variants.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        getServletContext().getRequestDispatcher("/user/variants.jsp").forward(request, response);
    }

}
