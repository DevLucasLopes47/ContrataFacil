package com.uni.servicehiring;

import com.uni.servicehiring.application.filter.ProviderFilterEngine;
import com.uni.servicehiring.application.specification.CategorySpecification;
import com.uni.servicehiring.domain.factory.UserFactory;
import com.uni.servicehiring.domain.model.Category;
import com.uni.servicehiring.domain.repository.InMemoryProviderRepository;
import com.uni.servicehiring.domain.service.ProviderService;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProviderServiceTest {

    @Test
    void shouldFilterByCategory() {

        var repository = new InMemoryProviderRepository();
        var service = new ProviderService(repository, new ProviderFilterEngine());

        var provider = UserFactory.createProvider(
                "1",
                "João",
                "joao@email.com",
                "11122233344",
                "31999990000",
                Set.of(Category.CARPENTRY),
                5.0,
                10
        );

        service.registerProvider(provider);

        var result = service.search(new CategorySpecification(Category.CARPENTRY));

        assertEquals(1, result.size());
        assertEquals("João", result.get(0).getName());
    }

    @Test
    void shouldReturnEmptyListWhenCategoryDoesNotMatch() {

        var repository = new InMemoryProviderRepository();
        var service = new ProviderService(repository, new ProviderFilterEngine());

        var provider = UserFactory.createProvider(
                "1",
                "João",
                "joao@email.com",
                "11122233344",
                "31999990000",
                Set.of(Category.CARPENTRY),
                5.0,
                10
        );

        service.registerProvider(provider);

        var result = service.search(new CategorySpecification(Category.CLEANING));

        assertEquals(0, result.size());
    }
}