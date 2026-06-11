package com.uni.servicehiring.application.specification;

import com.uni.servicehiring.domain.model.Provider;

public class RatingSpecification implements Specification {

    private final double minRating;

    public RatingSpecification(double minRating) {
        this.minRating = minRating;
    }

    public boolean isSatisfiedBy(Provider provider) {
        return provider.getRating() >= minRating;
    }
}
