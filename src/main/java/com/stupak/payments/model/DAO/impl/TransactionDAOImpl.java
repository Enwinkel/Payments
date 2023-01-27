package com.stupak.payments.model.DAO.impl;

import com.stupak.payments.model.builder.QueryBuilder;
import com.stupak.payments.model.builder.TransactionQueryBuilder;
import com.stupak.payments.model.connectionpool.ConnectionPool;
import com.stupak.payments.model.entity.Transaction;
import com.stupak.payments.model.DAO.ITransactionDAO;

import java.util.List;

public class TransactionDAOImpl implements ITransactionDAO {

  private static final String GET_ALL = "SELECT * FROM transactions";
  private static final String GET_BY_ID =
      "SELECT id, timestamp, account_id, amount, is_credit, description FROM transactions "
          + "WHERE id= ?";
  private static final String GET_BY_ACCOUNT_ID =
      "SELECT id, timestamp, account_id, amount, is_credit, description FROM transactions "
          + "WHERE account_id= ?";
  private static final String GET_BY_PAGE_PART1 = "SELECT * FROM transactions WHERE account_id=?";
  private static final String GET_BY_PAGE_PART2 = " LIMIT ? OFFSET ?";
  private static final String CREATE =
      "INSERT INTO transactions (timestamp, account_id, amount, is_credit, description) "
          + "VALUES(?, ?, ?, ?, ?)";
  private static final String UPDATE =
      "UPDATE transactions SET timestamp = ?, account_id = ?, amount = ?, is_credit = ?, "
          + "description = ? WHERE id = ?";
  private static final String DELETE = "DELETE FROM transactions WHERE id = ?";

  private static final String ORDER_BY_DATE = " ORDER BY timestamp";
  private static final String ORDER_BY_DATE_DESC = " ORDER BY timestamp DESC";
  private static final String ORDER_BY_AMOUNT = " ORDER BY amount";
  private static final String ORDER_BY_AMOUNT_DESC = " ORDER BY amount DESC";

  private ConnectionPool instance = ConnectionPool.getInstance();
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
  public List<Transaction> getByPage(int recordsPerPage, int currentPage, long id, String sorting){
    String query = GET_BY_PAGE_PART1;

    switch(sorting){
      case "new_to_old":
        query += ORDER_BY_DATE_DESC;
        break;
      case "old_to_new":
        query += ORDER_BY_DATE;
        break;
      case "amount_ascending":
        query += ORDER_BY_AMOUNT;
        break;
      case "amount_descending":
        query += ORDER_BY_AMOUNT_DESC;
        break;
    }

    query += GET_BY_PAGE_PART2;

    return queryBuilder.executeAndReturnList(instance, query, id, recordsPerPage, currentPage);
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
