package com.stupak.payments.controller.command.client;

import com.stupak.payments.appcontext.AppContext;
import com.stupak.payments.controller.Path;
import com.stupak.payments.controller.command.ICommand;
import com.stupak.payments.model.entity.Account;
import com.stupak.payments.model.entity.User;
import com.stupak.payments.model.service.IAccountService;
import com.stupak.payments.model.service.IContactDetailsService;
import com.stupak.payments.model.service.IUserService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class BlockAccountCommand implements ICommand {
    IAccountService accountService = AppContext.getInstance().getAccountService();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        HttpSession session = req.getSession();

        long id;
        String account_id = req.getParameter("account_id");

        if(account_id != null && !"".equals(account_id)) {
            id = Long.parseLong(account_id);
        } else{
            id = (Long)session.getAttribute("account_id");
        }

        String forward;

        Account account;
        if (id != 0) {
            account = accountService.find(id);
            session.setAttribute("account_id", account.getId());
        } else {
            account = (Account) session.getAttribute("account");
        }

        forward = blockUser(resp, accountService, account);

        return forward;
    }

    private String blockUser(HttpServletResponse resp, IAccountService accountService, Account account) {
        String forward = Path.COMMAND_TRANSACTIONS;
        if (account.getBlocked()) {
            account.setBlocked(false);
            accountService.update(account);
        } else {
            account.setBlocked(true);
            accountService.update(account);
        }
        try {
            resp.sendRedirect(forward);
            forward = Path.COMMAND_REDIRECT;
        } catch (IOException e) {
            forward = Path.PAGE_ERROR_PAGE;
        }
        return forward;
    }

}