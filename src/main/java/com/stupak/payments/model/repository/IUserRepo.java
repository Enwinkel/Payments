package com.stupak.payments.model.repository;

import com.stupak.payments.model.entity.Tariff;
import com.stupak.payments.model.entity.User;

import java.util.List;

public interface IUserRepo extends IEntityRepo<User> {

  User getByLogin(String login);

  List<User> getAllSortedById();

  List<Tariff> getTariffs(User user);

  void addLinksUsersHasTariffs(User user, String[] tariffsId);

  void deleteLinksUsersHasTariffs(User user);


}
