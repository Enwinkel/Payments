package com.stupak.payments.model.repository;


import com.stupak.payments.model.entity.Transaction;

import java.util.List;

/**
 * Transaction repository interface.
 *
 * @author Aleksey Serdyukov.
 */
public interface ITransactionRepo extends IEntityRepo<Transaction> {

  List<Transaction> getAllByAccount(long id);
}
