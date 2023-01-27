package com.stupak.payments.model.service.impl;


import com.stupak.payments.model.entity.Account;
import com.stupak.payments.model.repository.IAccountRepo;
import com.stupak.payments.model.service.IAccountService;

import java.util.List;

public class AccountServiceImpl implements IAccountService {
  private final IAccountRepo repo;

  public AccountServiceImpl(IAccountRepo repo) {
    this.repo = repo;
  }

  @Override
  public List<Account> findAll() {
    return this.repo.getAll();
  }

  @Override
  public Account find(long id) {
    return this.repo.getById(id);
  }

  @Override
  public void save(Account account) {
    this.repo.create(account);
  }

  @Override
  public void update(Account account) {
    this.repo.update(account);
  }

  @Override
  public void remove(long id) {
    this.repo.delete(id);
  }

  @Override
  public long getNumberContract() {
    return repo.newNumberContract();
  }

  @Override
  public long getNextIdValue() {
    return repo.getNextIdValue();
  }

  @Override
  public List<Account> getAllRequested() {
    return repo.getAllRequested();
  }

  @Override
  public List<Account> getAllByUser(long id) {
    return repo.getAllByUser(id);
  }

  @Override
  public List<Account> getAllByUserSorted(long id, String sorting) {return repo.getAllByUserSorted(id, sorting);}

  @Override
  public Account getAccountByNumber(long number) {
    return repo.getAccountByNumber(number);
  }

  @Override
  public Account getAccountById(long id) {
    return repo.getById(id);
  }


}
