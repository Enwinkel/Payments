package com.stupak.payments.model.builder;


import com.stupak.payments.model.entity.Account;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountQueryBuilder extends QueryBuilder<Account> {
  @Override
  public List<Account> getListOfResult(ResultSet rs) throws SQLException {
    List<Account> accounts = new ArrayList<>();
    while (rs.next()) {
      Account account = new Account();
      fillAccount(rs, account);
      accounts.add(account);
    }
    return accounts;
  }

  @Override
  public Account getResult(ResultSet rs) throws SQLException {
    Account account = new Account();
    while (rs.next()) {
      fillAccount(rs, account);
    }
    return account;
  }

  private void fillAccount(ResultSet rs, Account account) throws SQLException {
    account.setId(rs.getLong("id"));
    account.setNumber(rs.getLong("number"));
    account.setAccountName(rs.getString("account_name"));
    account.setBalance(rs.getBigDecimal("balance"));
    account.setBlocked(rs.getBoolean("blocked"));
    account.setUserId(rs.getLong("user_id"));
    account.setUnblockReq(rs.getBoolean("unblock_req"));
  }
}
