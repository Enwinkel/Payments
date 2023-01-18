package com.stupak.payments.model.service.impl;

import com.stupak.payments.model.repository.ITransactionRowsRepo;
import com.stupak.payments.model.service.ITransactionRowsService;

public class TransactionRowsServiceImpl implements ITransactionRowsService {
    private final ITransactionRowsRepo repo;

    public TransactionRowsServiceImpl(ITransactionRowsRepo repo) {
        this.repo = repo;
    }

    @Override
    public int getNumberOfRows(long id){
        return repo.getNumberOfRows(id);
    }
}
