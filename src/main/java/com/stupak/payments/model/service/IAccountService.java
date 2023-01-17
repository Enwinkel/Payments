package com.stupak.payments.model.service;


import com.stupak.payments.model.entity.Account;

import java.util.List;

public interface IAccountService {

  List<Account> findAll();

  Account find(long id);

  void save(Account account);

  void update(Account account);

  void remove(long id);
  List<Account> getAllByUser(long id);

  Account getAccountByNumber(long number);

  Account getAccountById(long id);

  public List<Account> getAllRequested();

  long getNumberContract();

  long getNextIdValue();

}
