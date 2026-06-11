package com.uni.servicehiring.domain.service;

import com.uni.servicehiring.application.filter.ProviderFilterEngine;
import com.uni.servicehiring.application.specification.Specification;
import com.uni.servicehiring.domain.model.Provider;
import com.uni.servicehiring.domain.repository.ProviderRepository;

import java.util.List;

public class ProviderService {

    private final ProviderRepository repository;
    private final ProviderFilterEngine filterEngine;

    public ProviderService(ProviderRepository repository,
                           ProviderFilterEngine filterEngine) {
        this.repository = repository;
        this.filterEngine = filterEngine;
    }

    public void registerProvider(Provider provider) {
        repository.save(provider);
    }

    public List<Provider> search(Specification spec) {
        return filterEngine.filter(repository.findAll(), spec);
    }
}
