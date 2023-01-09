package com.stupak.payments.model.repository;


import com.stupak.payments.model.entity.ContactDetails;

public interface IContactDetailsRepo extends IEntityRepo<ContactDetails> {
  long getNextIdValue();
}
