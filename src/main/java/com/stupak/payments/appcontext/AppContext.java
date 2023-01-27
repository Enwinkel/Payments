package com.stupak.payments.appcontext;

import com.stupak.payments.model.DAO.*;
import com.stupak.payments.model.DAO.impl.*;
import com.stupak.payments.model.service.*;
import com.stupak.payments.model.service.impl.*;

public class AppContext {
  private static volatile AppContext appContext = new AppContext();

  public static AppContext getInstance() {
    return appContext;
  }

  private final IUserDAO userRepo = new UserDAOImpl();
  private final IAccountDAO accountRepo = new AccountDAOImpl();
  private final IContactDetailsDAO contactDetailsRepo = new ContactDetailsDAOImpl();

  private final IServicesDAO servicesRepo = new ServicesDAOImpl();

  private final IContactDetailsService detailsService =
          new ContactDetailsServiceImpl(contactDetailsRepo);

  private final IAccountService accountService = new AccountServiceImpl(accountRepo);
  private final IUserService userService = new UserServiceImpl(userRepo, detailsService);
  private final IServicesService servicesService = new ServicesServiceImpl(servicesRepo);
  private final ITransactionDAO transactionRepo = new TransactionDAOImpl();
  private final ITransactionRowsDAO transactionRowsRepo = new TransactionRowsDAOImpl();
  private final ITransactionService transactionService = new TransactionServiceImpl(transactionRepo,
          accountService, userService);
  private final ITransactionRowsService transactionRowService = new TransactionRowsServiceImpl(transactionRowsRepo);
  public IServicesService getServicesService() {
    return servicesService;
  }
  public IUserService getUserService() {
    return userService;
  }

  public IContactDetailsService getDetailsService() {
    return detailsService;
  }

  public IAccountService getAccountService() {
    return accountService;
  }

  public ITransactionService getTransactionService() {
    return transactionService;
  }

  public ITransactionRowsService getTransactionRowService() {
    return transactionRowService;
  }
}
