package com.colonial.domain.model;

import com.colonial.domain.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class User {
    private int idUser;

    @NotBlank(message = "El nombre no puede estar vacío")
    private String name;

    @Email(message = "El correo electrónico no es válido")
    private String email;

    @NotBlank(message = "El teléfono no puede estar vacío")
    private String phone;

    @NotNull(message = "El rol es obligatorio")
    private Role role;

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
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
