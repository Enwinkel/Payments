package com.stupak.payments.controller.command.client;

import com.stupak.payments.appcontext.AppContext;
import com.stupak.payments.controller.Path;
import com.stupak.payments.controller.command.ICommand;
import com.stupak.payments.model.entity.Account;
import com.stupak.payments.model.entity.User;
import com.stupak.payments.model.service.IAccountService;
import com.stupak.payments.model.service.IUserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;


public class PersonalAccountCommand implements ICommand {
  private final IUserService userService =  AppContext.getInstance().getUserService();

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) {
    HttpSession session = request.getSession();
    String forward = Path.PAGE_ACCOUNT;

    User fullUser = (User) session.getAttribute("user");
    userService.updateFullUserToSession(request, session, fullUser);

    IAccountService as = AppContext.getInstance().getAccountService();
    List<Account> accounts = as.getAllByUser(fullUser.getId());
    session.setAttribute("accounts", accounts);

    return forward;
  }




}