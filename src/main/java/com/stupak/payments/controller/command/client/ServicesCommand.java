package com.stupak.payments.controller.command.client;


import com.stupak.payments.appcontext.AppContext;
import com.stupak.payments.controller.Path;
import com.stupak.payments.controller.command.ICommand;
import com.stupak.payments.model.entity.Account;
import com.stupak.payments.model.entity.Service;
import com.stupak.payments.model.entity.Tariff;
import com.stupak.payments.model.entity.User;
import com.stupak.payments.model.service.*;
import com.stupak.payments.util.ReportBuilder;

import java.io.IOException;
import java.math.BigDecimal;
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

        if (request.getParameter("amount") != null) {
            forward = doPayment(request, response);
        }

        List<Service> services = servicesService.findAll();

        IAccountService as = AppContext.getInstance().getAccountService();
        List<Account> accounts = as.getAllByUser(fullUser.getId());

        session.setAttribute("accounts", accounts);
        request.setAttribute("services", services);

        return forward;
    }

    private String doPayment(HttpServletRequest request, HttpServletResponse response) {
        String resp = Path.COMMAND_SERVICES;
        long account_number = Long.parseLong(request.getParameter("account_number"));

        Account account = accountService.getAccountByNumber(account_number);

        BigDecimal amount = BigDecimal.valueOf(Long.parseLong(request.getParameter("amount")));
        transactionService.doPayment(account, amount);

        try {
            response.sendRedirect(resp);
            resp = Path.COMMAND_REDIRECT;
        } catch (IOException e) {
            resp = Path.PAGE_ERROR_PAGE;
        }
        return resp;
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
