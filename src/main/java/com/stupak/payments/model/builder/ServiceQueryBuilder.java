package com.stupak.payments.model.builder;

import com.stupak.payments.model.entity.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ServiceQueryBuilder extends QueryBuilder<Service> {
  @Override
  public List<Service> getListOfResult(ResultSet rs) throws SQLException {
    List<Service> services = new ArrayList<>();
    while (rs.next()) {
      Service service = new Service();
      service.setId(rs.getLong("id"));
      service.setName(rs.getString("name"));
      service.setDescription(rs.getString("description"));
      services.add(service);
    }
    services.sort((left, right) -> (int) (left.getId() - right.getId()));
    return services;
  }

  @Override
  public Service getResult(ResultSet rs) throws SQLException {
    Service service = new Service();
    while (rs.next()) {
      service.setId(rs.getLong("id"));
      service.setName("name");
      service.setDescription(rs.getString("description"));
    }
    return service;
  }
}
