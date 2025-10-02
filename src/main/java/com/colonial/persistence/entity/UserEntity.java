    package com.colonial.persistence.entity;


    import jakarta.persistence.*;

    @Entity
    @Table(name = "users")
    public class UserEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id_user")
        private Integer idUser;

        @Column(length = 40, nullable = false)
        private String name;

        @Column(length = 50, unique = true)
        private String email;

        @Column(length = 10, unique = true, nullable = false)
        private String phone;

        @Column(nullable = false)
        private String role;

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

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }
    }
