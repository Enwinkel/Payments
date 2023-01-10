package com.stupak.payments.appcontext;

import com.stupak.payments.model.repository.IAccountRepo;
import com.stupak.payments.model.repository.IContactDetailsRepo;
import com.stupak.payments.model.repository.ITariffRepo;
import com.stupak.payments.model.repository.IUserRepo;
import com.stupak.payments.model.repository.impl.AccountRepoImpl;
import com.stupak.payments.model.repository.impl.ContactDetailsRepoImpl;
import com.stupak.payments.model.repository.impl.TariffRepoImpl;
import com.stupak.payments.model.repository.impl.UserRepoImpl;
import com.stupak.payments.model.service.IAccountService;
import com.stupak.payments.model.service.IContactDetailsService;
import com.stupak.payments.model.service.ITariffService;
import com.stupak.payments.model.service.IUserService;
import com.stupak.payments.model.service.impl.AccountServiceImpl;
import com.stupak.payments.model.service.impl.ContactDetailsServiceImpl;
import com.stupak.payments.model.service.impl.TariffServiceImpl;
import com.stupak.payments.model.service.impl.UserServiceImpl;

public class AppContext {
  private static volatile AppContext appContext = new AppContext();

  public static AppContext getInstance() {
    return appContext;
  }

  private final IUserRepo userRepo = new UserRepoImpl();
  private final IAccountRepo accountRepo = new AccountRepoImpl();
  private final IContactDetailsRepo contactDetailsRepo = new ContactDetailsRepoImpl();
  private final ITariffRepo tariffRepo = new TariffRepoImpl();

  private final IContactDetailsService detailsService =
          new ContactDetailsServiceImpl(contactDetailsRepo);

  private final IAccountService accountService = new AccountServiceImpl(accountRepo);
  private final IUserService userService = new UserServiceImpl(userRepo, detailsService);
  private final ITariffService tariffService = new TariffServiceImpl(tariffRepo);

  public IUserService getUserService() {
    return userService;
  }

  public IContactDetailsService getDetailsService() {
    return detailsService;
  }

  public IAccountService getAccountService() {
    return accountService;
  }

  public ITariffService getTariffService() {
    return tariffService;
  }
}
