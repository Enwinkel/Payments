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
        String forward;

        HttpSession session = req.getSession();

        forward = getNextPage(session);

        User fullUser = (User) session.getAttribute("user");
        userService.updateFullUserToSession(req, session, fullUser);
        List<Service> services = servicesService.findAll();

        Object done = session.getAttribute("done");
        session.removeAttribute("done");
        if(done != null) {
            forward = doPayment(req, resp);
            return forward;
        }

        Service service = services.get(Integer.parseInt(session.getAttribute("index").toString()));
        req.setAttribute("service_name", service.getName());

        return forward;
    }

    private String getNextPage(HttpSession session) {
        String forward;
        long account_number = Long.parseLong(session.getAttribute("account_number").toString());
        Account account = accountService.getAccountByNumber(account_number);

        BigDecimal balance = account.getBalance();
        BigDecimal amount = BigDecimal.valueOf(Long.parseLong(session.getAttribute("amount").toString()));

        if(balance.subtract(amount).compareTo(BigDecimal.ZERO) >= 0) {
             forward= Path.PAGE_PAYMENT;
        } else{
            forward= Path.PAGE_INSUFFICIENT;
        }
        return forward;
    }

    private String doPayment(HttpServletRequest req, HttpServletResponse resp) {
        String forward = Path.COMMAND_SERVICES;
        HttpSession session = req.getSession();
        long account_number = Long.parseLong(session.getAttribute("account_number").toString());

        Account account = accountService.getAccountByNumber(account_number);

        BigDecimal amount = BigDecimal.valueOf(Long.parseLong(session.getAttribute("amount").toString()));
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
