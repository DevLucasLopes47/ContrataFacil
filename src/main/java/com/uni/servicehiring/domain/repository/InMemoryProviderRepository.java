package com.uni.servicehiring.domain.repository;

import com.uni.servicehiring.domain.model.Provider;
import java.util.ArrayList;
import java.util.List;

public class InMemoryProviderRepository implements ProviderRepository {

    private final List<Provider> database = new ArrayList<>();

    public void save(Provider provider) {
        database.add(provider);
    }

    public List<Provider> findAll() {
        return new ArrayList<>(database);
    }
}
