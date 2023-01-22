package com.stupak.payments.controller.command.common;

import com.stupak.payments.controller.Path;
import com.stupak.payments.controller.command.ICommand;
import com.stupak.payments.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;

public class LocalizationCommand implements ICommand {
  @Override
  public String execute(HttpServletRequest req, HttpServletResponse resp) {
    HttpSession session = req.getSession();

    String fmtLocale = "javax.servlet.jsp.jstl.fmt.locale";
    String defaultLocale = "defaultLocale";

    if (session.getAttribute("ua") != null) {
      Config.set(session, fmtLocale, Path.LOCALE_NAME_UA);
      session.setAttribute(defaultLocale, "ua");
    } else {
      Config.set(session, fmtLocale, "en");
      session.setAttribute(defaultLocale, Path.LOCALE_NAME_EN);
    }
    session.removeAttribute("ua");

    User user = (User) session.getAttribute("user");
    return (user.getRoleId() == 1) ? "controller?action=users" : "controller?action=account";
  }
}