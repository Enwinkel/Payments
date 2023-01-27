package com.stupak.payments.model.service.impl;

import com.stupak.payments.model.entity.Service;
import com.stupak.payments.model.DAO.IServicesDAO;
import com.stupak.payments.model.service.IServicesService;

import java.util.List;

public class ServicesServiceImpl implements IServicesService {
  private final IServicesDAO repo;

  public ServicesServiceImpl(IServicesDAO repo) {
    this.repo = repo;
  }

  @Override
  public List<Service> findAll() {
    return this.repo.getAll();
  }

  @Override
  public Service find(long id) {
    return this.repo.getById(id);
  }

  @Override
  public Service find(String name) {
    return this.repo.getByName(name);
  }


  @Override
  public void save(Service service) {
    this.repo.create(service);
  }

  @Override
  public void update(Service service) {
    this.repo.update(service);
  }

  @Override
  public void remove(long id) {
    this.repo.delete(id);
  }
}
