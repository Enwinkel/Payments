package com.stupak.payments.controller.command.common;

import com.stupak.payments.controller.Path;
import com.stupak.payments.controller.command.ICommand;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements ICommand {
  private static final Logger log = Logger.getLogger(LogoutCommand.class);

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) {

    HttpSession session = request.getSession(false);
    if (session != null) {
      session.invalidate();
    }
    log.debug("Logout finished");
    return Path.PAGE_LOGIN;
  }
}
