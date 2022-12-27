package controller;

import controller.command.CommandFactory;
import controller.command.ICommand;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/controller")
public class Controller extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        CommandFactory commandFactory = CommandFactory.commandFactory();
        ICommand ic = commandFactory.getCommand(req);
        String page = ic.execute(req, resp);
        RequestDispatcher dispatcher = req.getRequestDispatcher(page);
        if (!page.equals("redirect")) {
            dispatcher.forward(req, resp);
        }
    }
}
