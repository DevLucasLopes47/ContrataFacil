package com.uni.servicehiring.domain.model;

import java.util.Set;

public class Provider extends User {

    private Set<Category> categories;
    private double rating;
    private int completedJobs;

    public Provider(String id, String name, String email,
                    Set<Category> categories,
                    double rating,
                    int completedJobs) {
        super(id, name, email);
        this.categories = categories;
        this.rating = rating;
        this.completedJobs = completedJobs;
    }

    public Set<Category> getCategories() { return categories; }
    public double getRating() { return rating; }
    public int getCompletedJobs() { return completedJobs; }
}
