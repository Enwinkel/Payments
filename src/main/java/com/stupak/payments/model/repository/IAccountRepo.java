package com.stupak.payments.model.repository;

import com.stupak.payments.model.entity.Account;

import java.util.List;

public interface IAccountRepo extends IEntityRepo<Account> {
  long newNumberContract();

  long getNextIdValue();

  List<Account> getAllByUser(long id);

  Account getAccountByNumber(long number);

    List<Account> getAllRequested();
}
