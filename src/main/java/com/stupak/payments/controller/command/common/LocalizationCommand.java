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
  public String execute(HttpServletRequest request, HttpServletResponse response) {
    HttpSession session = request.getSession();

    String fmtLocale = "javax.servlet.jsp.jstl.fmt.locale";
    String defaultLocale = "defaultLocale";

    if (request.getParameter("ru") != null) {
      Config.set(session, fmtLocale, Path.LOCALE_NAME_RU);
      session.setAttribute(defaultLocale, "ru");

    } else {
      Config.set(session, fmtLocale, "en");
      session.setAttribute(defaultLocale, Path.LOCALE_NAME_EN);
    }

    User user = (User) session.getAttribute("user");
    return (user.getRoleId() == 1) ? "controller?action=users" : "controller?action=account";
  }
}