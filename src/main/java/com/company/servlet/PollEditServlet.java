package com.company.servlet;

import com.company.PollEntity;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class PollEditServlet extends HttpServlet {
    public PollEditServlet(){
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PollEntity[] poll = new PollEntity[2];
        poll[0] = new PollEntity();
        poll[1] = new PollEntity();
        poll[0].setId(1);
        poll[0].setTitle("123");
        poll[1].setId(2);
        poll[1].setTitle("new title");

        request.setAttribute("polls", poll);

        request.getRequestDispatcher("mypage.jsp").forward(request, response);
        return;
//        ServletContext servletContext = getServletContext();
//
//        servletContext.setAttribute("name", "Tom");
//        servletContext.setAttribute("age", 35);

        //servletContext.getRequestDispatcher("index.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
