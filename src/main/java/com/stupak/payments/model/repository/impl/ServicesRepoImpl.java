package com.stupak.payments.model.repository.impl;


import com.stupak.payments.model.builder.QueryBuilder;
import com.stupak.payments.model.builder.ServiceQueryBuilder;
import com.stupak.payments.model.connectionpool.DBManager;
import com.stupak.payments.model.entity.Service;
import com.stupak.payments.model.repository.IServicesRepo;

import java.util.List;

public class ServicesRepoImpl implements IServicesRepo {
  private static final String GET_ALL = "SELECT * FROM services";
  private static final String GET_ALL_BY_SERVICES_ID =
      "SELECT * FROM tariffs WHERE services_id = ?";
  private static final String GET_BY_ID =
      "SELECT id, name, description, price, services_id FROM tariffs WHERE id = ?";
  private static final String GET_BY_NAME =
      "SELECT id, name, description, price, services_id FROM tariffs WHERE name = ?";
  private static final String CREATE =
      "INSERT INTO tariffs (name, price, description, services_id) VALUES (?, ?, ?, ?)";
  private static final String UPDATE =
      "UPDATE tariffs SET name = ?, description = ?, price = ? WHERE id = ?";
  private static final String DELETE = "DELETE FROM tariffs WHERE id = ?";

  private DBManager instance = DBManager.getInstance();
  private QueryBuilder queryBuilder = new ServiceQueryBuilder();

  @Override
  public List<Service> getAll() {
    return queryBuilder.executeAndReturnList(instance, GET_ALL);
  }

  @Override
  public List<Service> getAllByServiceId(long id) {
    return this.queryBuilder.executeAndReturnList(instance, GET_ALL_BY_SERVICES_ID, id);
  }

  @Override
  public Service getByName(String name) {
    return (Service) this.queryBuilder.executeAndReturn(instance, GET_BY_NAME, name);
  }

  @Override
  public Service getById(long id) {
    return (Service) queryBuilder.executeAndReturn(instance, GET_BY_ID, id);
  }


  @Override
  public void create(Service service) {
    queryBuilder.execute(instance, CREATE, service.getName(),
        service.getDescription(), service.getServiceId());
  }

  @Override
  public void update(Service service) {
    queryBuilder.execute(instance, UPDATE, service.getName(), service.getDescription(), service.getId());
  }

  @Override
  public void delete(long id) {
    queryBuilder.execute(instance, DELETE, id);
  }
}
