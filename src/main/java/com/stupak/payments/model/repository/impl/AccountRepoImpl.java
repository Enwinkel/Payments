package com.stupak.payments.model.repository.impl;

import com.stupak.payments.model.builder.AccountQueryBuilder;
import com.stupak.payments.model.builder.QueryBuilder;
import com.stupak.payments.model.connectionpool.DBManager;
import com.stupak.payments.model.entity.Account;
import com.stupak.payments.model.repository.IAccountRepo;

import java.util.List;

public class AccountRepoImpl implements IAccountRepo {
  private static final String CREATE = "INSERT INTO accounts (id, number, balance) "
      + "VALUES (?, ?, ?)";
  private static final String GET_ALL = "SELECT * FROM accounts";
  private static final String GET_BY_ID = "SELECT * FROM accounts WHERE id = ?";
  private static final String UPDATE = "UPDATE accounts SET balance = ?, blocked = ? WHERE id = ?";
  private static final String DELETE = "DELETE FROM accounts WHERE id = ?";
  private static final String GET_NEXT_AUTO_INCREMENT = "SELECT MAX(id)+1 FROM accounts";
  private static final String GET_BY_USER_ID =
          "SELECT * FROM accounts "
          + "WHERE user_id= ?";
  private static final String GET_BY_NUMBER =
          "SELECT * FROM accounts "
                  + "WHERE number= ?";
  private static final String GET_MAX_ID = "SELECT MAX(id) FROM accounts";

  private DBManager instance = DBManager.getInstance();
  private QueryBuilder queryBuilder = new AccountQueryBuilder();

  @Override
  public List<Account> getAll() {
    return this.queryBuilder.executeAndReturnList(instance, GET_ALL);
  }

  @Override
  public Account getById(long id) {
    return (Account) this.queryBuilder.executeAndReturn(instance, GET_BY_ID, id);
  }

  @Override
  public void create(Account account) {
    long id = queryBuilder.getNextAutoIncrement(instance, GET_NEXT_AUTO_INCREMENT);
    this.queryBuilder.execute(instance, CREATE, id, account.getNumber(), account.getBalance());
  }

  @Override
  public void update(Account account) {
    this.queryBuilder.execute(instance, UPDATE, account.getBalance(), account.getBlocked(), account.getId());
  }

  @Override
  public void delete(long id) {
    this.queryBuilder.execute(instance, DELETE, id);
  }

  @Override
  public long newNumberContract() {
    long accountNumber = 0;
    long id = queryBuilder.getNextAutoIncrement(instance, GET_MAX_ID);
    Account account = getById(id);

    if (account != null) {
      accountNumber = 1 + account.getNumber();
    }

    return accountNumber;
  }

  @Override
  public long getNextIdValue() {
    return queryBuilder.getNextAutoIncrement(instance, GET_NEXT_AUTO_INCREMENT);
  }

  @Override
  public List<Account> getAllByUser(long id) {
    return queryBuilder.executeAndReturnList(instance, GET_BY_USER_ID, id);
  }

  @Override
  public Account getAccountByNumber(long number) {
    return (Account)queryBuilder.executeAndReturn(instance, GET_BY_NUMBER, number);
  }

}
