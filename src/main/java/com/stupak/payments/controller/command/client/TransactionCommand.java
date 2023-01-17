package com.stupak.payments.controller.command.client;

import com.stupak.payments.appcontext.AppContext;
import com.stupak.payments.controller.Path;
import com.stupak.payments.controller.command.ICommand;
import com.stupak.payments.model.entity.Account;
import com.stupak.payments.model.entity.Transaction;
import com.stupak.payments.model.service.IAccountService;
import com.stupak.payments.model.service.ITransactionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class TransactionCommand implements ICommand {
  @Override
  public String execute(HttpServletRequest req, HttpServletResponse resp) {
    HttpSession session = req.getSession();
    String forward = Path.PAGE_TRANSACTIONS;
    long id;
    String account_id = req.getParameter("account_id");

    if(account_id != null) {
      id = Long.parseLong(account_id);
    } else{
      id = (Long)session.getAttribute("account_id");
    }

    ITransactionService ts = AppContext.getInstance().getTransactionService();
    List<Transaction> transactions = ts.getAllByAccount(id);

    IAccountService as = AppContext.getInstance().getAccountService();
    Account account = as.getAccountById(id);

    session.setAttribute("transactions", transactions);
    session.setAttribute("account_id", id);
    req.setAttribute("transactions", transactions);
    req.setAttribute("account", account);
    return forward;
  }
}