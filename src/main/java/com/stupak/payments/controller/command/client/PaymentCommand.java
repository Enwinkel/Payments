package com.stupak.payments.controller.command.client;

import com.stupak.payments.appcontext.AppContext;
import com.stupak.payments.controller.Path;
import com.stupak.payments.controller.command.ICommand;
import com.stupak.payments.model.entity.Account;
import com.stupak.payments.model.entity.Service;
import com.stupak.payments.model.entity.User;
import com.stupak.payments.model.service.IAccountService;
import com.stupak.payments.model.service.IServicesService;
import com.stupak.payments.model.service.ITransactionService;
import com.stupak.payments.model.service.IUserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public class PaymentCommand implements ICommand {

    private final ITransactionService transactionService =
            AppContext.getInstance().getTransactionService();
    private final IUserService userService = AppContext.getInstance().getUserService();
    private final IAccountService accountService = AppContext.getInstance().getAccountService();

    private final IServicesService servicesService = AppContext.getInstance().getServicesService();


    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        String forward = Path.PAGE_PAYMENT;

        User fullUser = (User) session.getAttribute("user");
        userService.updateFullUserToSession(req, session, fullUser);
        List<Service> services = servicesService.findAll();

        if("done".equals(req.getParameter("done"))) {
            forward = doPayment(req, resp);
            return forward;
        }

        Service service = services.get(Integer.parseInt(req.getParameter("index")));
        req.setAttribute("service_name", service.getName());

        session.setAttribute("account_number", req.getParameter("account_number"));
        session.setAttribute("amount", req.getParameter("amount"));

        return forward;
    }

    private String doPayment(HttpServletRequest req, HttpServletResponse resp) {
        String forward = Path.COMMAND_SERVICES;
        HttpSession session = req.getSession();
        long account_number = Long.parseLong((String)session.getAttribute("account_number"));

        Account account = accountService.getAccountByNumber(account_number);

        BigDecimal amount = BigDecimal.valueOf(Long.parseLong((String)session.getAttribute("amount")));
        transactionService.doPayment(account, amount);

        try {
            resp.sendRedirect(forward);
            forward = Path.COMMAND_REDIRECT;
        } catch (IOException e) {
            forward = Path.PAGE_ERROR_PAGE;
        }
        return forward;
    }
}
