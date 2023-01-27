package com.stupak.payments.model.service;

import com.stupak.payments.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


public interface IUserService {

  List<User> findAll();
  List<User> findAllSortedById();

  List<User> findAllFullInfo();

  User findByLoginFullInfo(String login);

  User find(long id);

  void save(User user);

  void update(User user);

  void remove(int id);

  User findByLogin(String login);


  void updateFullUserToSession(HttpServletRequest request, HttpSession session, User fullUser);
}
