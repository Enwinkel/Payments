package com.stupak.payments.model.repository;


import com.stupak.payments.model.entity.Transaction;

import java.util.List;

public interface ITransactionRepo extends IEntityRepo<Transaction> {

  List<Transaction> getAllByAccount(long id);
  List<Transaction> getByPage(int currentPage, int recordsPerPage, long id, String sorting);
}
