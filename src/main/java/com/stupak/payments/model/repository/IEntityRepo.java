package com.stupak.payments.model.repository;


import com.stupak.payments.model.entity.Entity;

import java.util.List;

public interface IEntityRepo<T extends Entity> {
  List<T> getAll();

  T getById(long id);

  void create(T t);

  void update(T t);

  void delete(long id);
}
