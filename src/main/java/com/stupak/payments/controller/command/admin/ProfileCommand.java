package com.stupak.payments.controller.command.admin;

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
import java.util.HashSet;
import java.util.List;


public class ProfileCommand implements ICommand {
  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) {
    HttpSession session = request.getSession();
    ServletContext servletContext = request.getServletContext();

    if (session.getAttribute("newUser") != null) {
      User newUser = (User) session.getAttribute("newUser");
      request.setAttribute("fullUser", newUser);
    }

    if (request.getParameter("user_id") != null) {
      long id = Long.parseLong(request.getParameter("user_id"));
      session.setAttribute("user_id", id);
      show(request, id);
    }

    if (session.getAttribute("user_id") != null) {
      Long id = (Long) session.getAttribute("user_id");
      show(request, id);
    }

    return Path.PAGE_PROFILE;
  }

  private void show(HttpServletRequest req, long id) {
    IUserService userService = AppContext.getInstance().getUserService();
    IContactDetailsService detailsService = AppContext.getInstance().getDetailsService();
    IAccountService as = AppContext.getInstance().getAccountService();
    HttpSession session = req.getSession();

    User user = userService.find(id);
    user.setRoleId(user.getRoleId());
    user.setDetails(detailsService.find(user.getDetails().getId()));

    List<Account> accounts = as.getAllByUser(user.getId());
    session.setAttribute("accounts", accounts);

    req.setAttribute("fullUser", user);
  }
}
