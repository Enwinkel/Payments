package com.stupak.payments.model.entity;

public class User extends Entity {

    private static final long serialVersionUID = 1L;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String surname;
    private boolean blocked;
    private int roleId;

    private ContactDetails details;


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public boolean getBlocked() { return blocked; }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public ContactDetails getDetails() {
        return details;
    }

    public void setDetails(ContactDetails details) {
        this.details = details;
    }


}
