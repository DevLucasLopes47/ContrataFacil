package com.uni.servicehiring.domain.model;

public class Client extends User {

    private String password;

    public Client(String id, String name, String email, String password) {
        super(id, name, email);

        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("Senha é obrigatória.");
        }

        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
