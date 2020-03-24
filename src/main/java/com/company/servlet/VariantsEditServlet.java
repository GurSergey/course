package com.company.servlet;

import com.company.db.VariantsRepositoryDB;
import com.company.enitities.VariantEntity;
import com.company.enums.EntityError;
import com.company.exceptions.DeleteException;
import com.company.exceptions.InsertException;
import com.company.exceptions.SelectException;
import com.company.exceptions.UpdateException;
import com.company.services.VariantsService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class VariantsEditServlet extends HttpServlet {
    public VariantsEditServlet(){
        super();
    }

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
        if(questionId==NOT_FOUND_ID) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        try {
            VariantsService service = new VariantsService(new VariantsRepositoryDB());
            context.setAttribute("variants", service.getVariantsByIdQuestion(questionId));
        } catch (SelectException e) {
            context.setAttribute("error", EntityError.SELECT);
        }
        getServletContext().getRequestDispatcher("/admin/variants.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int questionId = getPollId(request);
        if(questionId==NOT_FOUND_ID){
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        VariantEntity variantEntity = new VariantEntity();
        variantEntity.setId(request.getParameterMap().containsKey("id") ? Integer.parseInt(request.getParameter("id"))
                : -1);
        variantEntity.setQuestionId( Integer.parseInt(request.getParameter("questionId")));
        variantEntity.setText(request.getParameter("pollId"));

        ServletContext context = this.getServletContext();
        try {
            VariantsService service = new VariantsService(new VariantsRepositoryDB());

            if(request.getParameterMap().containsKey("save")){
                service.saveVariant(variantEntity);
                context.setAttribute("error", EntityError.NO_ERROR_INSERT);
            }
            if(request.getParameterMap().containsKey( "update")){
                service.updateVariant(variantEntity);
                context.setAttribute("error", EntityError.NO_ERROR_UPDATE);
            }
            if(request.getParameterMap().containsKey("delete")){
                service.deleteVariant(variantEntity);
                context.setAttribute("error", EntityError.NO_ERROR_DELETE);
            }
            context.setAttribute("polls", service.getVariantsByIdQuestion(questionId));
        } catch (SelectException e){
            context.setAttribute("error", EntityError.SELECT);
        } catch (InsertException e){
            context.setAttribute("error", EntityError.INSERT);
        } catch (UpdateException e) {
            context.setAttribute("error", EntityError.UPDATE);
        } catch (DeleteException e) {
            context.setAttribute("error", EntityError.DELETE);
        }
        getServletContext().getRequestDispatcher("/admin/variants.jsp").forward(request, response);
    }

}
