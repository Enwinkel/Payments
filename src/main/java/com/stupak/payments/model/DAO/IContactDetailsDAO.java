package com.stupak.payments.model.DAO;


import com.stupak.payments.model.entity.ContactDetails;

public interface IContactDetailsDAO extends IEntityDAO<ContactDetails> {
  long getNextIdValue();
}
