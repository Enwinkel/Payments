package com.stupak.payments.model.service;


import com.stupak.payments.model.entity.Account;
import com.stupak.payments.model.entity.Transaction;
import com.stupak.payments.model.entity.User;

import java.math.BigDecimal;
import java.util.List;

/**
 * Transaction service interface .
 *
 * @author Aleksey Serdyukov.
 */
public interface ITransactionService {


  void save(Transaction transaction);

  List<Transaction> getAllByAccount(long id);
  List<Transaction> getByPage(int currentPage, int recordsPerPage, long id);

  /**
   * Create top-up transaction.
   */
  void topUp(Account account, BigDecimal amount);

  void doPayment(Account account, BigDecimal amount);
}
