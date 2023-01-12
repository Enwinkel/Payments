package com.stupak.payments.controller.command.client;


import com.stupak.payments.appcontext.AppContext;
import com.stupak.payments.controller.Path;
import com.stupak.payments.controller.command.ICommand;
import com.stupak.payments.model.entity.Service;
import com.stupak.payments.model.entity.Tariff;
import com.stupak.payments.model.entity.User;
import com.stupak.payments.model.service.*;
import com.stupak.payments.util.ReportBuilder;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ServicesCommand implements ICommand {
    private final ITransactionService transactionService =
            AppContext.getInstance().getTransactionService();
    private final IUserService userService = AppContext.getInstance().getUserService();
    private final IAccountService accountService = AppContext.getInstance().getAccountService();
    private final IServicesService servicesService = AppContext.getInstance().getServicesService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String forward = Path.PAGE_SERVICES;

        User fullUser = (User) session.getAttribute("user");
        userService.updateFullUserToSession(request, session, fullUser);


        List<Service> services = servicesService.findAll();


        request.setAttribute("services", services);

        return forward;
    }

    private void printService(HttpServletRequest request, HttpServletResponse response, User user) {
        long tariffId = Long.parseLong(request.getParameter("service_id"));
        Set<Tariff> tariffs = user.getTariffs();
        for (Tariff tariff : tariffs) {
            if (tariff.getId() == tariffId) {
                ReportBuilder.tariffPdf(response, tariff);
            }
        }
    }
}
