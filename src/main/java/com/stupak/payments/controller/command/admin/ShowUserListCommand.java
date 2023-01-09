package com.stupak.payments.controller.command.admin;

import com.stupak.payments.appcontext.AppContext;
import com.stupak.payments.controller.Path;
import com.stupak.payments.controller.command.ICommand;
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
    IUserService userService = AppContext.getInstance().getUserService();
    IContactDetailsService detailsService = AppContext.getInstance().getDetailsService();
    IAccountService accountService = AppContext.getInstance().getAccountService();

    List<User> users = userService.findAll();
    List<User> fullUser = new ArrayList<>();
    for (User user : users) {
      user.setRoleId(user.getRoleId());
      user.setDetails(detailsService.find(user.getDetails().getId()));
      user.setAccount(accountService.find(user.getAccount().getId()));
      fullUser.add(user);
    }
    request.setAttribute("fullUser", fullUser);

    return Path.PAGE_USER_LIST;
  }
}