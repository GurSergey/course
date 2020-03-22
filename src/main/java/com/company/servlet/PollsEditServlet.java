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
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

public class PollsEditServlet extends HttpServlet {
    public PollsEditServlet(){
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext context = this.getServletContext();
        try {
            PollsService service = new PollsService(new PollsRepositoryDB());
            context.setAttribute("polls", service.getPolls());
        } catch (SelectException e){
            context.setAttribute("error", EntityError.SELECT);
        }
        getServletContext().getRequestDispatcher("polls.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PollEntity pollEntity = new PollEntity();
        pollEntity.setId(Integer.parseInt(request.getParameter("id")));
        pollEntity.setTitle( request.getParameter("title"));
        pollEntity.setVisible( Boolean.getBoolean(request.getParameter("visible")));
        pollEntity.setCreateDate(new Date((new java.util.Date()).getTime()));
        pollEntity.setStartDate(Date.valueOf(request.getParameter("currentDate")));
        pollEntity.setDateTo(Date.valueOf(request.getParameter("dateTo")));
        ServletContext context = this.getServletContext();
        try {
            PollsService service = new PollsService(new PollsRepositoryDB());
            
            switch (request.getParameter("typeReq"))
            {
                case "save":
                    service.savePoll(pollEntity);
                    break;
                case "update":
                    service.updatePoll(pollEntity);
                    break;
                case "delete":
                    service.deletePoll(pollEntity);
                    break;
            }
            context.setAttribute("polls", service.getPolls());
            context.setAttribute("error", EntityError.NO);
        } catch (SelectException e){
            context.setAttribute("error", EntityError.SELECT);
        } catch (InsertException e){
            context.setAttribute("error", EntityError.INSERT);
        } catch (UpdateException e) {
            context.setAttribute("error", EntityError.UPDATE);
        } catch (DeleteException e) {
            context.setAttribute("error", EntityError.DELETE);
        }
        getServletContext().getRequestDispatcher("polls.jsp").forward(request, response);
    }
}