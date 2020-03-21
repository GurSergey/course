package com.company.servlet;

import com.company.db.PollsRepositoryDB;
import com.company.services.PollsService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PollResultsServlet extends HttpServlet {
    public PollResultsServlet(){
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
