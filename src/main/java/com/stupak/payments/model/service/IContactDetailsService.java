package com.stupak.payments.model.service;


import com.stupak.payments.model.entity.ContactDetails;

import java.util.List;

public interface IContactDetailsService {

  List<ContactDetails> findAll();

  ContactDetails find(long id);

  void save(ContactDetails account);

  void update(ContactDetails account);

  void remove(int id);

  long getNextIdValue();
}
