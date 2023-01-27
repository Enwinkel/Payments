package com.stupak.payments.model.entity;

import java.util.Objects;

public class Service extends Entity {
  private static final long serialVersionUID = 1L;
  private String name;
  private String description;
  private long serviceId;

  public Service() {
  }

  /**
   * All args constructor.
   */
  public Service(String name, String description, long serviceId) {
    this.name = name;
    this.description = description;
    this.serviceId = serviceId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public long getServiceId() {
    return serviceId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Service)) {
      return false;
    }
    Service tariff = (Service) o;
    return Objects.equals(name, tariff.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }
}
