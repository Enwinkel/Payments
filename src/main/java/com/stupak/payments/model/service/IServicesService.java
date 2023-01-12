package com.stupak.payments.model.service;


import com.stupak.payments.model.entity.Service;

import java.util.List;

public interface IServicesService {

  List<Service> findAll();

  Service find(long id);

  Service find(String name);

  void save(Service service);

  void update(Service service);

  void remove(long id);
}
