package com.stupak.payments.model.repository;


import com.stupak.payments.model.entity.Service;

import java.util.List;

public interface IServicesRepo extends IEntityRepo<Service> {

  List<Service> getAllByServiceId(long id);

  Service getByName(String name);
}
