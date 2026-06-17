package com.uni.servicehiring.domain.model;

public abstract class User {
    protected String id;
    protected String name;
    protected String email;

    public User(String id, String name, String email) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("ID é obrigatório.");
        }

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Nome é obrigatório.");
        }

        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("E-mail é obrigatório.");
        }

        this.id = id;
        this.name = name;
        this.email = email;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
}
