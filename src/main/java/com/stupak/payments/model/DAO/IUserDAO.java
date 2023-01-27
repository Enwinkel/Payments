package com.stupak.payments.model.DAO;

import com.stupak.payments.model.entity.User;

import java.util.List;

public interface IUserDAO extends IEntityDAO<User> {

  User getByLogin(String login);

  List<User> getAllSortedById();



}
