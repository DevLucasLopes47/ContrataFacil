package com.uni.servicehiring.domain.model;

import java.util.Set;
import java.util.HashSet;

public class Provider extends User {

    private String cpf;
    private String phone;
    private Set<Category> categories;
    private double rating;
    private int ratingCount;
    private int completedJobs;

    public Provider(String id,
                    String name,
                    String email,
                    String cpf,
                    String phone,
                    Set<Category> categories,
                    double rating,
                    int completedJobs) {

        super(id, name, email);

        if (cpf == null || cpf.isBlank()) {
            throw new IllegalArgumentException("CPF é obrigatório.");
        }

        if (phone == null || phone.isBlank()) {
            throw new IllegalArgumentException("Telefone é obrigatório.");
        }

        if (categories == null || categories.isEmpty()) {
            throw new IllegalArgumentException("Categoria é obrigatória.");
        }

        if (rating < 0 || rating > 5) {
            throw new IllegalArgumentException("Avaliação inicial deve estar entre 0 e 5.");
        }

        if (completedJobs < 0) {
            throw new IllegalArgumentException("Quantidade de serviços concluídos não pode ser negativa.");
        }

        this.cpf = cpf;
        this.phone = phone;
        this.categories = new HashSet<>(categories);
        this.rating = rating;
        this.ratingCount = rating > 0 ? 1 : 0;
        this.completedJobs = completedJobs;
    }

    public void registerRating(double newRating) {
        if (newRating < 1 || newRating > 5) {
            throw new IllegalArgumentException("A nota da avaliação deve estar entre 1 e 5.");
        }

        double total = this.rating * this.ratingCount;
        total += newRating;
        this.ratingCount++;

        this.rating = total / this.ratingCount;
    }

    public void incrementCompletedJobs() {
        this.completedJobs++;
    }

    public String getCpf() {
        return cpf;
    }

    public String getPhone() {
        return phone;
    }

    public Set<Category> getCategories() {
        return new HashSet<>(categories);
    }

    public double getRating() {
        return rating;
    }

    public int getRatingCount() {
        return ratingCount;
    }

    public int getCompletedJobs() {
        return completedJobs;
    }
}
