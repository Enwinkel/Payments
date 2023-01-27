package com.stupak.payments.appcontext;

import com.stupak.payments.model.repository.*;
import com.stupak.payments.model.repository.impl.*;
import com.stupak.payments.model.service.*;
import com.stupak.payments.model.service.impl.*;

public class AppContext {
  private static volatile AppContext appContext = new AppContext();

  public static AppContext getInstance() {
    return appContext;
  }

  private final IUserRepo userRepo = new UserRepoImpl();
  private final IAccountRepo accountRepo = new AccountRepoImpl();
  private final IContactDetailsRepo contactDetailsRepo = new ContactDetailsRepoImpl();

  private final IServicesRepo servicesRepo = new ServicesRepoImpl();

  private final IContactDetailsService detailsService =
          new ContactDetailsServiceImpl(contactDetailsRepo);

  private final IAccountService accountService = new AccountServiceImpl(accountRepo);
  private final IUserService userService = new UserServiceImpl(userRepo, detailsService);
  private final IServicesService servicesService = new ServicesServiceImpl(servicesRepo);
  private final ITransactionRepo transactionRepo = new TransactionRepoImpl();
  private final ITransactionRowsRepo transactionRowsRepo = new TransactionRowsRepoImpl();
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
