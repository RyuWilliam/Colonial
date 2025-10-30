package com.colonial.domain.model;
import com.colonial.domain.enums.Role;


public class User {
    private Integer idUser;
    private String name;
    private String email;
    private String phone;
    private Role role;

    public User() {
    }

    public User(Integer idUser, String name, String email, String phone, Role role) {
        this.idUser = idUser;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.role = role;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}