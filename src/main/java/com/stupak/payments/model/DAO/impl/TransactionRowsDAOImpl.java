package com.stupak.payments.model.DAO.impl;

import com.stupak.payments.model.builder.QueryBuilder;
import com.stupak.payments.model.builder.TransactionRowsQueryBuilder;
import com.stupak.payments.model.connectionpool.ConnectionPool;
import com.stupak.payments.model.entity.Rows;
import com.stupak.payments.model.DAO.ITransactionRowsDAO;

public class TransactionRowsDAOImpl implements ITransactionRowsDAO {
    private static final String NUMBER_OF_ROWS = "SELECT COUNT(id) FROM transactions WHERE account_id = ?";
    private ConnectionPool instance = ConnectionPool.getInstance();

    private QueryBuilder queryBuilder = new TransactionRowsQueryBuilder();
    @Override
    public int getNumberOfRows(long id){
        Rows rows = (Rows)queryBuilder.executeAndReturn(instance, NUMBER_OF_ROWS, id);
        return rows.getNumber();
    }
}
