package com.stupak.payments.model.service.impl;


import com.stupak.payments.model.entity.Tariff;
import com.stupak.payments.model.repository.ITariffRepo;
import com.stupak.payments.model.service.ITariffService;

import java.util.List;

/**
 * Tariff service interface implementation.
 *
 * @author Aleksey Serdyukov.
 */
public class TariffServiceImpl implements ITariffService {
  private final ITariffRepo repo;

  public TariffServiceImpl(ITariffRepo repo) {
    this.repo = repo;
  }

  @Override
  public List<Tariff> findAll() {
    return this.repo.getAll();
  }

  @Override
  public Tariff find(long id) {
    return this.repo.getById(id);
  }

  @Override
  public Tariff find(String name) {
    return this.repo.getByName(name);
  }

  @Override
  public List<Tariff> findAllById(long id) {
    return this.repo.getAllByServiceId(id);
  }

  @Override
  public void save(Tariff tariff) {
    this.repo.create(tariff);
  }

  @Override
  public void update(Tariff tariff) {
    this.repo.update(tariff);
  }

  @Override
  public void remove(long id) {
    this.repo.delete(id);
  }
}
