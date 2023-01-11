package com.stupak.payments.model.service.impl;

import com.stupak.payments.model.entity.Account;
import com.stupak.payments.model.entity.Transaction;
import com.stupak.payments.model.entity.User;
import com.stupak.payments.model.repository.ITransactionRepo;
import com.stupak.payments.model.service.IAccountService;
import com.stupak.payments.model.service.ITransactionService;
import com.stupak.payments.model.service.IUserService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class TransactionServiceImpl implements ITransactionService {
  private final ITransactionRepo repo;
  private final IAccountService accountService;
  private final IUserService userService;

  /**
   * All args constructor.
   */
  public TransactionServiceImpl(ITransactionRepo repo, IAccountService accountService,
                                IUserService userService) {
    this.repo = repo;
    this.accountService = accountService;
    this.userService = userService;
  }

  @Override
  public BigDecimal calcTransactionsByAccount(long id) {
    List<Transaction> transactions = repo.getAllByAccount(id);
    BigDecimal result = BigDecimal.ZERO;
    for (Transaction t : transactions) {
      result = (t.isCredit()) ? result.add(t.getAmount()) : result.subtract(t.getAmount());
    }
    return result;
  }



  @Override
  public void recalcBalanceAndBlockByAllUsers() {
    List<User> users = userService.findAllFullInfo();
    for (User user : users) {
      Account account = new Account();
      BigDecimal balance = calcTransactionsByAccount(account.getId());
      account.setBalance(balance);
      accountService.update(account);

      if (!user.isBlocked() && balance.compareTo(BigDecimal.ZERO) < 0) {
        user.setBlocked(true);
        userService.update(user);
      }
    }
  }

  @Override
  public void topUp(User user, BigDecimal amount) {
    Transaction transaction = new Transaction();
    transaction.setTimestamp(LocalDateTime.now());
    transaction.setAccount(new Account().getId());
    transaction.setCredit(true);
    transaction.setAmount(amount);
    transaction.setDescription("Top up account");
    save(transaction);

    Account account = new Account();
    account.setBalance(calcTransactionsByAccount(account.getId()));
    accountService.update(account);

    if (user.isBlocked() && new Account().getBalance().compareTo(BigDecimal.ZERO) > 0) {
      user.setBlocked(false);
      userService.update(user);
    }
  }


  @Override
  public void save(Transaction transaction) {
    repo.create(transaction);
  }

  @Override
  public List<Transaction> getAllByAccount(long id) {
    return repo.getAllByAccount(id);
  }
}
