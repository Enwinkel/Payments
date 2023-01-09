package com.stupak.payments.model.repository;

import com.stupak.payments.model.entity.User;

import java.util.List;

public interface IUserRepo extends IEntityRepo<User> {

  User getByLogin(String login);

}
