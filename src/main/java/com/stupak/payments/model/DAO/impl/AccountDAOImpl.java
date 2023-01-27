package com.stupak.payments.model.DAO.impl;

import com.stupak.payments.model.builder.AccountQueryBuilder;
import com.stupak.payments.model.builder.QueryBuilder;
import com.stupak.payments.model.connectionpool.ConnectionPool;
import com.stupak.payments.model.entity.Account;
import com.stupak.payments.model.DAO.IAccountDAO;

import java.util.List;

public class AccountDAOImpl implements IAccountDAO {
    private static final String CREATE = "INSERT INTO accounts (id, number, account_name, balance, user_id, blocked, unblock_req) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String GET_ALL = "SELECT * FROM accounts";
    private static final String GET_BY_ID = "SELECT * FROM accounts WHERE id = ?";
    private static final String UPDATE = "UPDATE accounts SET balance = ?, account_name = ?, user_id = ?, " +
            "blocked = ?, unblock_req = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM accounts WHERE id = ?";
    private static final String GET_NEXT_AUTO_INCREMENT = "SELECT MAX(id)+1 FROM accounts";
    private static final String GET_BY_USER_ID = "SELECT * FROM accounts "
            + "WHERE user_id= ?";
    private static final String GET_REQUESTED = "SELECT * FROM accounts WHERE unblock_req = true";
    private static final String GET_BY_NUMBER = "SELECT * FROM accounts "
            + "WHERE number= ?";
    private static final String GET_MAX_ID = "SELECT MAX(id) FROM accounts";

    private static final String ORDER_BY_NUMBER = " ORDER BY number";
    private static final String ORDER_BY_NUMBER_DESC = " ORDER BY number DESC";
    private static final String ORDER_BY_NAME = " ORDER BY account_name";
    private static final String ORDER_BY_NAME_DESC = " ORDER BY account_name DESC";
    private static final String ORDER_BY_BALANCE = " ORDER BY balance";
    private static final String ORDER_BY_BALANCE_DESC = " ORDER BY balance DESC";

    private ConnectionPool instance = ConnectionPool.getInstance();
    private QueryBuilder queryBuilder = new AccountQueryBuilder();

    @Override
    public List<Account> getAll() {
        return this.queryBuilder.executeAndReturnList(instance, GET_ALL);
    }

    @Override
    public Account getById(long id) {
        return (Account) this.queryBuilder.executeAndReturn(instance, GET_BY_ID, id);
    }

    @Override
    public void create(Account account) {
        long id = queryBuilder.getNextAutoIncrement(instance, GET_NEXT_AUTO_INCREMENT);
        this.queryBuilder.execute(instance, CREATE, id, account.getNumber(), account.getAccountName(), account.getBalance(), account.getUserId(),
                account.getBlocked(), account.isUnblockReq());
    }

    @Override
    public void update(Account account) {
        this.queryBuilder.execute(instance, UPDATE, account.getBalance(), account.getAccountName(), account.getUserId(),
                account.getBlocked(), account.isUnblockReq(), account.getId());
    }

    @Override
    public void delete(long id) {
        this.queryBuilder.execute(instance, DELETE, id);
    }

    @Override
    public long newNumberContract() {
        long accountNumber = 0;
        long id = queryBuilder.getNextAutoIncrement(instance, GET_MAX_ID);
        Account account = getById(id);

        if (account != null) {
            accountNumber = 1 + account.getNumber();
        }

        return accountNumber;
    }

    @Override
    public long getNextIdValue() {
        return queryBuilder.getNextAutoIncrement(instance, GET_NEXT_AUTO_INCREMENT);
    }

    @Override
    public List<Account> getAllByUser(long id) {
        return queryBuilder.executeAndReturnList(instance, GET_BY_USER_ID, id);
    }

    @Override
    public List<Account> getAllByUserSorted(long id, String sorting) {
        String query = GET_BY_USER_ID;

        switch(sorting){
            case "number_ascending":
                query += ORDER_BY_NUMBER;
                break;
            case "number_descending":
                query += ORDER_BY_NUMBER_DESC;
                break;
            case "name_ascending":
                query += ORDER_BY_NAME;
                break;
            case "name_descending":
                query += ORDER_BY_NAME_DESC;
                break;
            case "amount_ascending":
                query += ORDER_BY_BALANCE;
                break;
            case "amount_descending":
                query += ORDER_BY_BALANCE_DESC;
                break;
        }

        return queryBuilder.executeAndReturnList(instance, query, id);
    }

    @Override
    public Account getAccountByNumber(long number) {
        return (Account) queryBuilder.executeAndReturn(instance, GET_BY_NUMBER, number);
    }

    @Override
    public List<Account> getAllRequested() {
        return queryBuilder.executeAndReturnList(instance, GET_REQUESTED);
    }

}
