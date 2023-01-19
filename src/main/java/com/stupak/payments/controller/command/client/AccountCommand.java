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


public class AccountCommand implements ICommand {
  private final IUserService userService =  AppContext.getInstance().getUserService();

  @Override
  public String execute(HttpServletRequest req, HttpServletResponse resp){
    HttpSession session = req.getSession();
    String forward = Path.PAGE_ACCOUNT;

    User fullUser = (User) session.getAttribute("user");
    userService.updateFullUserToSession(req, session, fullUser);

    String sorting = req.getParameter("account_sorting");

    if (sorting == null) {
      sorting = (String) session.getAttribute("account_sorting");
      if (sorting == null) {
        sorting = "number_ascending";
      }
    }

    IAccountService as = AppContext.getInstance().getAccountService();
    List<Account> accounts = as.getAllByUserSorted(fullUser.getId(), sorting);
    session.setAttribute("accounts", accounts);
    session.setAttribute("account_sorting", sorting);

    return forward;
  }




}