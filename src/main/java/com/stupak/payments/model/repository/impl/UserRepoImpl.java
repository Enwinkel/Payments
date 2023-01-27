package com.stupak.payments.model.repository.impl;

import com.stupak.payments.model.builder.QueryBuilder;
import com.stupak.payments.model.builder.UserQueryBuilder;
import com.stupak.payments.model.connectionpool.ConnectionPool;
import com.stupak.payments.model.entity.User;
import com.stupak.payments.model.repository.IUserRepo;

import java.util.List;

public class UserRepoImpl implements IUserRepo {
  private static final String GET_ALL = "SELECT * FROM users";
  private static final String GET_ALL_SORTED_BY_ID = "SELECT * FROM users ORDER BY id";
  private static final String GET_BY_ID =
      "SELECT id, login, password, first_name, last_name, surname, roles_id, "
          + "contact_details_id, blocked FROM users WHERE id = ?";
  private static final String GET_BY_LOGIN =
      "SELECT id, login, password, first_name, last_name, surname, roles_id, "
          + "contact_details_id, blocked FROM users WHERE login = ?";
  private static final String CREATE =
      "INSERT INTO users (id, login, password, first_name, last_name, surname, blocked, "
          + "roles_id, contact_details_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
  private static final String UPDATE =
      "UPDATE users SET login = ?, password = ?, first_name = ?, last_name = ?, surname = ?, "
          + "blocked = ?, roles_id = ? WHERE id = ?";
  private static final String DELETE = "DELETE FROM users WHERE id = ?";

  private static final String ADD_LINK_USERS_HAS_TRAFFICS =
      "INSERT INTO users_has_tariffs (users_id, tariffs_id) VALUES (?, ?)";
  private static final String GET_LINK_USERS_HAS_TRAFFICS =
      "SELECT t.id, t.name, t.description, t.price, t.services_id FROM tariffs AS t JOIN "
          + "users_has_tariffs AS uht ON t.id = uht.tariffs_id AND uht.users_id = "
          + "(SELECT id FROM users WHERE id = ?)";
  private static final String DELETE_LINK_USERS_HAS_TRAFFICS =
      "DELETE FROM users_has_tariffs WHERE users_id = ?";

  private static final String GET_NEXT_AUTO_INCREMENT = "SELECT MAX(id)+1 from users";

  private final ConnectionPool instance = ConnectionPool.getInstance();
  private final QueryBuilder queryBuilder = new UserQueryBuilder();

  @Override
  public List<User> getAll() {
    return queryBuilder.executeAndReturnList(instance, GET_ALL);
  }

  @Override
  public List<User> getAllSortedById() {
    return queryBuilder.executeAndReturnList(instance, GET_ALL_SORTED_BY_ID);
  }

  @Override
  public User getById(long id) {
    return (User) queryBuilder.executeAndReturn(instance, GET_BY_ID, id);
  }

  @Override
  public void create(User user) {

    long id = queryBuilder.getNextAutoIncrement(instance, GET_NEXT_AUTO_INCREMENT);
    queryBuilder.execute(instance, CREATE, id, user.getLogin(), user.getPassword(),
        user.getFirstName(), user.getLastName(), user.getSurname(), user.isBlocked(),
        user.getRoleId(), user.getDetails().getId());
  }

  @Override
  public void update(User user) {

    queryBuilder.execute(instance, UPDATE, user.getLogin(), user.getPassword(),
        user.getFirstName(), user.getLastName(), user.getSurname(), user.isBlocked(),
        user.getRoleId(), user.getId());
  }

  @Override
  public void delete(long id) {
    queryBuilder.execute(instance, DELETE, id);
  }

  @Override
  public User getByLogin(String login) {
    return (User) queryBuilder.executeAndReturn(instance, GET_BY_LOGIN, login);
  }


}
