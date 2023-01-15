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
import java.util.List;

public class ServicesCommand implements ICommand {
    private final IUserService userService = AppContext.getInstance().getUserService();
    private final IServicesService servicesService = AppContext.getInstance().getServicesService();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        String forward = Path.PAGE_SERVICES;

        User fullUser = (User) session.getAttribute("user");
        userService.updateFullUserToSession(req, session, fullUser);

        List<Service> services = servicesService.findAll();

        IAccountService as = AppContext.getInstance().getAccountService();
        List<Account> accounts = as.getAllByUser(fullUser.getId());

        session.setAttribute("accounts", accounts);
        req.setAttribute("services", services);

        return forward;
    }

}
