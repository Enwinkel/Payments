package com.stupak.payments.model.DAO;


import com.stupak.payments.model.entity.Service;

import java.util.List;

public interface IServicesDAO extends IEntityDAO<Service> {

  List<Service> getAllByServiceId(long id);

  Service getByName(String name);
}
