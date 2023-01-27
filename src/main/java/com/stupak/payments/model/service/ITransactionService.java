package com.stupak.payments.model.service;


import com.stupak.payments.model.entity.Account;
import com.stupak.payments.model.entity.Transaction;

import java.math.BigDecimal;
import java.util.List;

public interface ITransactionService {


  void save(Transaction transaction);

  List<Transaction> getAllByAccount(long id);
  List<Transaction> getByPage(int currentPage, int recordsPerPage, long id, String sorting);

  /**
   * Create top-up transaction.
   */
  void topUp(Account account, BigDecimal amount);

  void doPayment(Account account, BigDecimal amount);
}
