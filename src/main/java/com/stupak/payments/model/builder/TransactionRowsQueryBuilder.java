package com.stupak.payments.model.builder;


import com.stupak.payments.model.entity.Rows;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TransactionRowsQueryBuilder extends QueryBuilder<Rows> {

  @Override
  public List<Rows> getListOfResult(ResultSet rs) throws SQLException {
    return null;
  }

  @Override
  public Rows getResult(ResultSet rs) throws SQLException {
    rs.next();
    Rows rows = new Rows();
    rows.setNumber(rs.getInt("count"));
    return rows;
  }
}
