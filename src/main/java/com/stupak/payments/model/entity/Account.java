package com.stupak.payments.model.entity;

import java.math.BigDecimal;

public class Account extends Entity {
  private static final long serialVersionUID = 1L;
  private long number;
  private BigDecimal balance;
  private boolean blocked;
  private long userId;
  private boolean unblockReq;

  public Account() {
  }

  public Account(long id) {
    super(id);
  }

  public long getNumber() {
    return number;
  }

  public void setNumber(long number) {
    this.number = number;
  }

  public BigDecimal getBalance() {
    return balance;
  }

  public void setBalance(BigDecimal balance) {
    this.balance = balance;
  }

  public void setBlocked(boolean blocked){
    this.blocked = blocked;
  }

  public boolean getBlocked(){
    return blocked;
  }

  public long getUserId() {
    return userId;
  }

  public void setUserId(long user_id) {
    this.userId = user_id;
  }

  public boolean isBlocked() {
    return blocked;
  }

  public boolean isUnblockReq() {
    return unblockReq;
  }

  public void setUnblockReq(boolean unblockReq) {
    this.unblockReq = unblockReq;
  }
}
