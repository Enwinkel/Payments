package com.stupak.payments.model.repository.impl;

import com.stupak.payments.model.builder.QueryBuilder;
import com.stupak.payments.model.builder.TransactionQueryBuilder;
import com.stupak.payments.model.connectionpool.DBManager;
import com.stupak.payments.model.entity.Transaction;
import com.stupak.payments.model.repository.ITransactionRepo;

import java.util.List;

public class TransactionRepoImpl implements ITransactionRepo {

  private static final String GET_ALL = "SELECT * FROM transactions";
  private static final String GET_BY_ID =
      "SELECT id, timestamp, account_id, amount, is_credit, description FROM transactions "
          + "WHERE id= ?";
  private static final String GET_BY_ACCOUNT_ID =
      "SELECT id, timestamp, account_id, amount, is_credit, description FROM transactions "
          + "WHERE account_id= ?";
  private static final String GET_BY_PAGE = "SELECT * FROM transactions WHERE account_id=? LIMIT ? OFFSET ?";
  private static final String CREATE =
      "INSERT INTO transactions (timestamp, account_id, amount, is_credit, description) "
          + "VALUES(?, ?, ?, ?, ?)";
  private static final String UPDATE =
      "UPDATE transactions SET timestamp = ?, account_id = ?, amount = ?, is_credit = ?, "
          + "description = ? WHERE id = ?";
  private static final String DELETE = "DELETE FROM transactions WHERE id = ?";

  private DBManager instance = DBManager.getInstance();
  private QueryBuilder queryBuilder = new TransactionQueryBuilder();

  @Override
  public List<Transaction> getAllByAccount(long id) {
    return queryBuilder.executeAndReturnList(instance, GET_BY_ACCOUNT_ID, id);
  }

  @Override
  public List<Transaction> getAll() {
    return queryBuilder.executeAndReturnList(instance, GET_ALL);
  }

  @Override
  public Transaction getById(long id) {

    return (Transaction) queryBuilder.executeAndReturn(instance, GET_BY_ID, id);
  }
  @Override
  public List<Transaction> getByPage(int recordsPerPage, int currentPage, long id){
    return queryBuilder.executeAndReturnList(instance, GET_BY_PAGE, id, recordsPerPage, currentPage);
  }

  @Override
  public void create(Transaction transaction) {
    queryBuilder.execute(instance, CREATE, transaction.getTimestamp(), transaction.getAccount(),
        transaction.getAmount(), transaction.isCredit(), transaction.getDescription());
  }

  @Override
  public void update(Transaction transaction) {
    queryBuilder.execute(instance, UPDATE, transaction.getTimestamp(), transaction.getAccount(),
        transaction.getAmount(), transaction.isCredit(), transaction.getDescription());
  }

  @Override
  public void delete(long id) {
    queryBuilder.execute(instance, UPDATE, id);
  }
}
