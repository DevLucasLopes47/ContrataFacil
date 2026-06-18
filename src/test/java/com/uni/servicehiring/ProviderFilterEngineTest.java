package com.uni.servicehiring;

import com.uni.servicehiring.application.filter.ProviderFilterEngine;
import com.uni.servicehiring.application.specification.CategorySpecification;
import com.uni.servicehiring.domain.factory.UserFactory;
import com.uni.servicehiring.domain.model.Category;
import com.uni.servicehiring.domain.model.Provider;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProviderFilterEngineTest {

    @Test
    void shouldFilterProvidersUsingSpecification() {

        Provider provider1 = UserFactory.createProvider(
                "1",
                "João",
                "joao@email.com",
                "11122233344",
                "31999990000",
                Set.of(Category.CARPENTRY),
                4.8,
                10
        );

        Provider provider2 = UserFactory.createProvider(
                "2",
                "Pedro",
                "pedro@email.com",
                "55566677788",
                "31988880000",
                Set.of(Category.CLEANING),
                4.5,
                8
        );

        ProviderFilterEngine engine = new ProviderFilterEngine();

        List<Provider> result = engine.filter(
                List.of(provider1, provider2),
                new CategorySpecification(Category.CARPENTRY)
        );

        assertEquals(1, result.size());
        assertEquals("João", result.get(0).getName());
    }
}