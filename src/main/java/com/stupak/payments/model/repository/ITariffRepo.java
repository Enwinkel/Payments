package com.stupak.payments.model.repository;


import com.stupak.payments.model.entity.Tariff;

import java.util.List;

/**
 * Tariff repository interface.
 *
 * @author Aleksey Serdyukov.
 */
public interface ITariffRepo extends IEntityRepo<Tariff> {

  List<Tariff> getAllByServiceId(long id);

  Tariff getByName(String name);
}
