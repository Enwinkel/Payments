package com.stupak.payments.model.service;


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
  /**
   * Calculate all transactions.
   *
   * @return  Account amount.
   */
  BigDecimal calcTransactionsByAccount(long id);

  void save(Transaction transaction);

  List<Transaction> getAllByAccount(long id);

  /**
   * Create top-up transaction.
   */
  void topUp(User user, BigDecimal amount);

  /**
   * Checks if user has negative account amount and blocks it.
   */
  void recalcBalanceAndBlockByAllUsers();
}
