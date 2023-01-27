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
import java.util.HashSet;
import java.util.List;


public class ShowUserListCommand implements ICommand {
  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) {
    IUserService us = AppContext.getInstance().getUserService();
    IAccountService as = AppContext.getInstance().getAccountService();
    IContactDetailsService detailsService = AppContext.getInstance().getDetailsService();

    List<User> users = us.findAllSortedById();
    List<Account> accounts;
    List<User> fullUser = new ArrayList<>();
    List<User> usersReq = new ArrayList<>();

    for (User user : users) {
      user.setRoleId(user.getRoleId());
      user.setDetails(detailsService.find(user.getDetails().getId()));
      fullUser.add(user);
    }

    accounts = as.getAllRequested();

    for(Account account : accounts){
      usersReq.add(us.find(account.getUserId()));
    }

    request.setAttribute("fullUser", fullUser);
    request.setAttribute("usersReq", usersReq);

    return Path.PAGE_USER_LIST;
  }
}