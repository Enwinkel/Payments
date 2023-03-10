package com.stupak.payments.model.builder;

import com.stupak.payments.model.connectionpool.ConnectionPool;
import com.stupak.payments.model.entity.Entity;

import java.sql.*;
import java.util.List;

public abstract class QueryBuilder<T extends Entity> {
  /**
   * Lets you know the next automatic id postgres.
   *
   * @param instance the DataSource instance
   * @param query    the database query
   * @return next automatic id postgres
   */
  public final int getNextAutoIncrement(final ConnectionPool instance, final String query) {
    int nextid = -1;
    Connection conn = instance.getConnection();
    try (PreparedStatement statement = conn.prepareStatement(query)) {
      try (ResultSet rs = statement.executeQuery()) {
        while (rs.next()) {
          nextid = rs.getInt(1);
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    instance.closeConnection(conn);
    return nextid;
  }

  /**
   * Execute query (CREATE, UPDATE, DELETE) the database.
   *
   * @param instance the DataSource instance
   * @param query    the database query
   */
  public final void execute(final ConnectionPool instance, final String query) {
    executeQuery(instance, query, new Object[0]);
  }

  /**
   * Execute query (CREATE, UPDATE, DELETE) the database.
   *
   * @param instance the DataSource instance
   * @param query    the database query
   * @param args     the args
   */
  public final void execute(final ConnectionPool instance, final String query, Object... args) {
    executeQuery(instance, query, args);
  }

  /**
   * Reading from database.
   *
   * @param instance the DataSource instance
   * @param query    the database query
   * @return the list of {@link com.stupak.payments.model.entity.Entity}
   */
  public final List<T> executeAndReturnList(final ConnectionPool instance, final String query,
                                            Object... args) {
    return executeAndReturnValues(instance, query, args);
  }

  /**
   * Reading from database by parameter.
   *
   * @param instance the DataSource instance
   * @param query    the database query
   * @param args     the args
   * @return the list of {@link com.stupak.payments.model.entity.Entity}
   */
  public final T executeAndReturn(final ConnectionPool instance, final String query, Object... args) {
    return executeAndReturnValue(instance, query, args);
  }

  /**
   * QueryBuilder for methods {@link #execute(ConnectionPool, String),
   * {@link #execute(ConnectionPool, String, Object...)}}.
   *
   * @param instance the DataSource instance
   * @param query    the database query
   * @param args     the args
   */
  private void executeQuery(final ConnectionPool instance, String query, Object... args) {
    Connection conn = instance.getConnection();
    try (PreparedStatement st = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
      setArgsOfPreparedStatement(st, args);
      st.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    instance.closeConnection(conn);
  }

  /**
   * QueryBuilder for methods {@link #executeAndReturnList(ConnectionPool, String, Object...)} ,
   * {@link #executeAndReturn(ConnectionPool, String, Object...)}}.
   *
   * @param instance the DataSource instance
   * @param query    the database query
   * @return the list of {@link com.stupak.payments.model.entity.Entity}
   */
  private List<T> executeAndReturnValues(final ConnectionPool instance, final String query,
                                         Object... args) {
    List<T> models = null;
    Connection conn = instance.getConnection();
    try (PreparedStatement statement = conn.prepareStatement(query,
        Statement.RETURN_GENERATED_KEYS)) {
      setArgsOfPreparedStatement(statement, args);
      try (ResultSet rs = statement.executeQuery()) {
        models = getListOfResult(rs);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    instance.closeConnection(conn);
    return models;
  }

  /**
   * QueryBuilder for methods {@link #executeAndReturn(ConnectionPool, String, Object...)} ,
   * {@link #executeAndReturn(ConnectionPool, String, Object...)}}.
   *
   * @param instance the DataSource instance
   * @param query    the database query
   * @param args     the args
   * @return the list of {@link com.stupak.payments.model.entity.Entity}
   */
  private T executeAndReturnValue(final ConnectionPool instance, final String query, Object... args) {
    T model = null;
    Connection conn = instance.getConnection();
    try (PreparedStatement statement = conn.prepareStatement(query,
        Statement.RETURN_GENERATED_KEYS)) {
      setArgsOfPreparedStatement(statement, args);
      try (ResultSet rs = statement.executeQuery()) {
        model = getResult(rs);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    instance.closeConnection(conn);
    return model;
  }

  /**
   * Sets args of prepared statement.
   * Sets the value of the designated parameter using the given object.
   * The JDBC specification specifies a standard mapping from Java Object types to SQL types.
   * The given argument will be converted to the corresponding SQL type before being sent
   * to the database.
   *
   * @param statement the PreparedStatement
   * @param args      the args
   * @throws SQLException hte SQL exception
   */
  private void setArgsOfPreparedStatement(PreparedStatement statement, Object... args)
      throws SQLException {
    for (int i = 0; i < args.length; i++) {
      statement.setObject(i + 1, args[i]);
    }
  }

  /**
   * Creates objects.
   *
   * @param rs the ResultSet
   * @return the list of {@link com.stupak.payments.model.entity.Entity}
   * @throws SQLException hte SQL exception
   */
  public abstract List<T> getListOfResult(ResultSet rs) throws SQLException;


  /**
   * Creates object.
   *
   * @param rs the ResultSet
   * @return the list of {@link com.stupak.payments.model.entity.Entity}
   * @throws SQLException hte SQL exception
   */
  public abstract T getResult(ResultSet rs) throws SQLException;
}