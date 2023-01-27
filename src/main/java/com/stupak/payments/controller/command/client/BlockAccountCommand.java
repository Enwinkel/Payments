package com.stupak.payments.controller.command.client;

import com.stupak.payments.appcontext.AppContext;
import com.stupak.payments.controller.Path;
import com.stupak.payments.controller.command.ICommand;
import com.stupak.payments.model.entity.Account;
import com.stupak.payments.model.service.IAccountService;

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
        String account_id;

        account_id = session.getAttribute("account_id").toString();
        id = Long.parseLong(account_id);

        String forward;

        Account account;
        if (id != 0) {
            account = accountService.find(id);
            session.setAttribute("account_id", account.getId());
        } else {
            account = (Account) session.getAttribute("account");
        }

        forward = blockAccount(req, resp, accountService, account);

        return forward;
    }

    private String blockAccount(HttpServletRequest req, HttpServletResponse resp, IAccountService accountService, Account account) {
        HttpSession session = req.getSession();
        Object admin = session.getAttribute("admin");
        String forward = Path.COMMAND_TRANSACTIONS;

        if("profile".equals(admin)){
            forward = Path.COMMAND_PROFILE;
        } else if("requests".equals(admin)){
            forward = Path.COMMAND_REQUESTS;
        }

        if (account.getBlocked() && !(admin == null)){
            account.setBlocked(false);
            account.setUnblockReq(false);
        } else if(account.getBlocked()){
            account.setUnblockReq(true);
        } else {
            account.setBlocked(true);
        }

        accountService.update(account);

        try {
            resp.sendRedirect(forward);
            forward = Path.COMMAND_REDIRECT;
        } catch (IOException e) {
            forward = Path.PAGE_ERROR_PAGE;
        }
        return forward;
    }

}