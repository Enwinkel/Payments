package com.stupak.payments.controller.command.admin;

import com.stupak.payments.appcontext.AppContext;
import com.stupak.payments.controller.Path;
import com.stupak.payments.controller.command.ICommand;
import com.stupak.payments.model.entity.Account;
import com.stupak.payments.model.entity.User;
import com.stupak.payments.model.service.IAccountService;
import com.stupak.payments.model.service.IContactDetailsService;
import com.stupak.payments.model.service.IUserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class RequestCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        IUserService us = AppContext.getInstance().getUserService();
        IAccountService as = AppContext.getInstance().getAccountService();
        List<Account> accounts;
        List<User> usersReq = new ArrayList<>();

        accounts = as.getAllRequested();

        for(Account account : accounts){
            usersReq.add(us.find(account.getUserId()));
        }

        request.setAttribute("accounts", accounts);
        request.setAttribute("usersReq", usersReq);

        return Path.PAGE_REQUESTS;
    }
}
