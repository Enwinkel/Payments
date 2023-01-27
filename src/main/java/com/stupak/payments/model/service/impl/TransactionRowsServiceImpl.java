package com.stupak.payments.model.service.impl;

import com.stupak.payments.model.DAO.ITransactionRowsDAO;
import com.stupak.payments.model.service.ITransactionRowsService;

public class TransactionRowsServiceImpl implements ITransactionRowsService {
    private final ITransactionRowsDAO repo;

    public TransactionRowsServiceImpl(ITransactionRowsDAO repo) {
        this.repo = repo;
    }

    @Override
    public int getNumberOfRows(long id){
        return repo.getNumberOfRows(id);
    }
}
