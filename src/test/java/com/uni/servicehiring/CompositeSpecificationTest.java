package com.uni.servicehiring;

import com.uni.servicehiring.application.specification.CategorySpecification;
import com.uni.servicehiring.application.specification.CompositeSpecification;
import com.uni.servicehiring.application.specification.RatingSpecification;
import com.uni.servicehiring.domain.factory.UserFactory;
import com.uni.servicehiring.domain.model.Category;
import com.uni.servicehiring.domain.model.Provider;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CompositeSpecificationTest {

    @Test
    void shouldSatisfyWhenAllSpecificationsAreSatisfied() {

        Provider provider = UserFactory.createProvider(
                "1",
                "João",
                "joao@email.com",
                "11122233344",
                "31999990000",
                Set.of(Category.ELECTRICAL),
                4.8,
                10
        );

        CompositeSpecification specification = new CompositeSpecification(
                new CategorySpecification(Category.ELECTRICAL),
                new RatingSpecification(4.5)
        );

        assertTrue(specification.isSatisfiedBy(provider));
    }

    @Test
    void shouldNotSatisfyWhenOneSpecificationIsNotSatisfied() {

        Provider provider = UserFactory.createProvider(
                "1",
                "João",
                "joao@email.com",
                "11122233344",
                "31999990000",
                Set.of(Category.ELECTRICAL),
                4.8,
                10
        );

        CompositeSpecification specification = new CompositeSpecification(
                new CategorySpecification(Category.CLEANING),
                new RatingSpecification(4.5)
        );

        assertFalse(specification.isSatisfiedBy(provider));
    }
}