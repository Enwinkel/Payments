package com.stupak.payments.controller;

import com.stupak.payments.controller.command.CommandFactory;
import com.stupak.payments.controller.command.ICommand;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

public class Controller extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String page = processRequest(req, resp);

        if(page.contains("controller?action=")){
            resp.sendRedirect(page);
            return;
        }

        if (!page.equals("redirect")) {
            RequestDispatcher dispatcher = req.getRequestDispatcher(page);
            dispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();

        String action = req.getParameter("action");
        String redirect = "controller?action=" + action;

        Map<String,String[]> parameters = req.getParameterMap();

        for (Map.Entry<String,String[]> parameter : parameters.entrySet()) {
            session.setAttribute(parameter.getKey(), parameter.getValue()[0]);
        }
        resp.sendRedirect(redirect);
    }

    private String processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        CommandFactory commandFactory = CommandFactory.commandFactory();
        ICommand ic = commandFactory.getCommand(req);
        return ic.execute(req, resp);
    }
}
