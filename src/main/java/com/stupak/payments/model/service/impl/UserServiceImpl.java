package com.stupak.payments.model.service.impl;

import com.stupak.payments.model.entity.User;
import com.stupak.payments.model.repository.IUserRepo;
import com.stupak.payments.model.service.IContactDetailsService;
import com.stupak.payments.model.service.IUserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements IUserService {

  private final IUserRepo repo;
  private final IContactDetailsService detailsService;

  /**
   * All args constructor.
   */
  public UserServiceImpl(IUserRepo repo, IContactDetailsService detailsService) {
    this.repo = repo;
    this.detailsService = detailsService;
  }


  @Override
  public List<User> findAll() {
    return this.repo.getAll();
  }

  @Override
  public List<User> findAllSortedById() {
    return this.repo.getAllSortedById();
  }

  @Override
  public List<User> findAllFullInfo() {
    List<User> users = findAll();
    List<User> fullUser = new ArrayList<>();
    for (User user : users) {
      fullUser.add(userToFullUser(user));
    }
    return fullUser;
  }

  @Override
  public User findByLoginFullInfo(String login) {
    return userToFullUser(findByLogin(login));
  }

  @Override
  public User find(long id) {
    return this.repo.getById(id);
  }


  @Override
  public void save(User user) {
    this.repo.create(user);
  }

  @Override
  public void update(User user) {
    this.repo.update(user);
  }

  @Override
  public void remove(int id) {
    this.repo.delete(id);
  }

  @Override
  public User findByLogin(String login) {
    return this.repo.getByLogin(login);
  }



  @Override
  public void updateFullUserToSession(HttpServletRequest request, HttpSession session, User user) {
    User fullUser = userToFullUser(user);
    session.setAttribute("fullUser", fullUser);
    request.setAttribute("fullUser", fullUser);
  }

  private User userToFullUser(User user) {
    user.setDetails(detailsService.find(user.getDetails().getId()));
    return user;

  }
}
