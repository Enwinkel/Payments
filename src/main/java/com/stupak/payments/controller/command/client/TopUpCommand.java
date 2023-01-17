package com.stupak.payments.controller.command.client;

import com.stupak.payments.appcontext.AppContext;
import com.stupak.payments.controller.Path;
import com.stupak.payments.controller.command.ICommand;
import com.stupak.payments.model.entity.Account;
import com.stupak.payments.model.entity.Service;
import com.stupak.payments.model.entity.Transaction;
import com.stupak.payments.model.entity.User;
import com.stupak.payments.model.service.IAccountService;
import com.stupak.payments.model.service.IServicesService;
import com.stupak.payments.model.service.ITransactionService;
import com.stupak.payments.model.service.IUserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public class TopUpCommand implements ICommand {

  private final ITransactionService transactionService =
          AppContext.getInstance().getTransactionService();
  private final IAccountService accountService = AppContext.getInstance().getAccountService();


  @Override
  public String execute(HttpServletRequest req, HttpServletResponse resp) {
    String forward = Path.PAGE_TRANSACTIONS;

    if (req.getParameter("amount") != null) {
      forward = topUpAccount(req, resp);
      return forward;
    }

    return forward;
  }

  private String topUpAccount(HttpServletRequest req, HttpServletResponse resp) {
    String forward = Path.COMMAND_TRANSACTIONS;
    long account_number = Long.parseLong(req.getParameter("account_number"));

    Account account = accountService.getAccountByNumber(account_number);

    BigDecimal amount = BigDecimal.valueOf(Long.parseLong(req.getParameter("amount")));
    transactionService.topUp(account, amount);

    try {
      resp.sendRedirect(forward);
      forward = Path.COMMAND_REDIRECT;
    } catch (IOException e) {
      forward = Path.PAGE_ERROR_PAGE;
    }
    return forward;
  }
}
