package com.stupak.payments.controller.command.client;

import com.stupak.payments.appcontext.AppContext;
import com.stupak.payments.controller.Path;
import com.stupak.payments.controller.command.ICommand;
import com.stupak.payments.model.entity.Account;
import com.stupak.payments.model.entity.Transaction;
import com.stupak.payments.model.service.IAccountService;
import com.stupak.payments.model.service.ITransactionRowsService;
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

    IAccountService as = AppContext.getInstance().getAccountService();
    ITransactionService ts = AppContext.getInstance().getTransactionService();
    ITransactionRowsService trs = AppContext.getInstance().getTransactionRowService();

    long id;
    int currentPage;
    int recordsPerPage = 6;

    String account_id = req.getParameter("account_id");
    if(account_id != null) {
      id = Long.parseLong(account_id);
    } else{
      id = (Long)session.getAttribute("account_id");
    }

    String currentPageStr = req.getParameter("current_page");
    if(currentPageStr != null) {
      currentPage = Integer.parseInt(currentPageStr);
    } else{
      currentPage = (Integer)session.getAttribute("current_page");
    }

    String sorting = req.getParameter("account_sorting");

    if (sorting == null) {
      sorting = (String) session.getAttribute("account_sorting");
      if (sorting == null) {
        sorting = "new_to_old";
      }
    }

    List<Transaction> transactions = ts.getByPage(recordsPerPage,
            (currentPage - 1) * recordsPerPage, id, sorting);

    Account account = as.getAccountById(id);
    int rows = trs.getNumberOfRows(id);
    int nOfPages = rows / recordsPerPage;
    if ((rows % recordsPerPage) != 0 && nOfPages % recordsPerPage > 0) {

      nOfPages++;
    }

    session.setAttribute("account_id", id);
    session.setAttribute("current_page", currentPage);
    session.setAttribute("account_sorting", sorting);
    req.setAttribute("transactions", transactions);
    req.setAttribute("account", account);
    req.setAttribute("no_of_pages", nOfPages);
    req.setAttribute("current_page", currentPage);
    req.setAttribute("records_per_page", recordsPerPage);


    return forward;
  }
}
