package com.stupak.payments.model.DAO;


import com.stupak.payments.model.entity.Transaction;

import java.util.List;

public interface ITransactionDAO extends IEntityDAO<Transaction> {

  List<Transaction> getAllByAccount(long id);
  List<Transaction> getByPage(int currentPage, int recordsPerPage, long id, String sorting);
}
