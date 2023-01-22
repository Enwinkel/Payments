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

public class TopUpCommand implements ICommand {

    private final ITransactionService transactionService =
            AppContext.getInstance().getTransactionService();
    private final IUserService userService = AppContext.getInstance().getUserService();
    private final IAccountService accountService = AppContext.getInstance().getAccountService();


    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        String forward = Path.PAGE_TRANSACTIONS;

        User fullUser = (User) session.getAttribute("user");
        userService.updateFullUserToSession(req, session, fullUser);

        if (session.getAttribute("amount") != null) {
            forward = topUp(req, resp);
            return forward;
        }

        return forward;
    }

    private String topUp(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        String forward = Path.COMMAND_TRANSACTIONS;
        forward = forward + "&currentPage=1";
        long id = Long.parseLong(session.getAttribute("account_id").toString());

        Account account = accountService.getAccountById(id);

        BigDecimal amount = BigDecimal.valueOf(Long.parseLong(session.getAttribute("amount").toString()));
        transactionService.topUp(account, amount);

        try {
            resp.sendRedirect(forward);
            forward = Path.COMMAND_REDIRECT;
        } catch (IOException e) {
            forward = Path.PAGE_ERROR_PAGE;
        }
        return forward;
    }
}
