package com.company.servlet;

import com.company.db.PollsRepositoryDB;
import com.company.enitities.Entity;
import com.company.enitities.PollEntity;
import com.company.enums.EntityError;
import com.company.exceptions.DeleteException;
import com.company.exceptions.InsertException;
import com.company.exceptions.SelectException;
import com.company.exceptions.UpdateException;
import com.company.services.PollsService;


import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;


public class PollsEditServlet extends HttpServlet {
    public PollsEditServlet(){
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext context = this.getServletContext();
        try {
            PollsService service = new PollsService(new PollsRepositoryDB());
            request.setAttribute("polls", service.getPolls());
        } catch (SelectException e){
            request.setAttribute("error", EntityError.SELECT);
        }
        getServletContext().getRequestDispatcher("/admin/polls.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PollEntity pollEntity = new PollEntity();
        pollEntity.setId(request.getParameterMap().containsKey("id")?Integer.parseInt(request.getParameter("id"))
                : -1);
        pollEntity.setTitle( request.getParameter("title"));
        pollEntity.setVisible(request.getParameter("visible") != null);
        pollEntity.setCreateDate(new Date((new java.util.Date()).getTime()));
        pollEntity.setStartDate(Date.valueOf(request.getParameter("startDate")));
        pollEntity.setDateTo(Date.valueOf(request.getParameter("dateTo")));
        ServletContext context = this.getServletContext();
        try {
            PollsService service = new PollsService(new PollsRepositoryDB());

            if(request.getParameterMap().containsKey("save")){
                service.savePoll(pollEntity);
                request.setAttribute("error", EntityError.NO_ERROR_INSERT);
            }
            if(request.getParameterMap().containsKey( "update")){
                service.updatePoll(pollEntity);
                request.setAttribute("error", EntityError.NO_ERROR_UPDATE);
            }
            if(request.getParameterMap().containsKey("delete")){
                service.deletePoll(pollEntity);
                request.setAttribute("error", EntityError.NO_ERROR_DELETE);
            }
            request.setAttribute("polls", service.getPolls());
        } catch (SelectException e){
            request.setAttribute("error", EntityError.SELECT);
        } catch (InsertException e){
            request.setAttribute("error", EntityError.INSERT);
        } catch (UpdateException e) {
            request.setAttribute("error", EntityError.UPDATE);
        } catch (DeleteException e) {
            request.setAttribute("error", EntityError.DELETE);
        }
        getServletContext().getRequestDispatcher("/admin/polls.jsp").forward(request, response);
    }
}
