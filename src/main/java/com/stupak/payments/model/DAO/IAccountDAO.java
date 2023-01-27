package com.stupak.payments.model.DAO;

import com.stupak.payments.model.entity.Account;

import java.util.List;

public interface IAccountDAO extends IEntityDAO<Account> {
  long newNumberContract();

  long getNextIdValue();

  List<Account> getAllByUser(long id);

  Account getAccountByNumber(long number);
  public List<Account> getAllRequested();

  List<Account> getAllByUserSorted(long id, String sorting);
}
