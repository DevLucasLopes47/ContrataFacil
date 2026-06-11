package com.uni.servicehiring.application.filter;

import com.uni.servicehiring.application.specification.Specification;
import com.uni.servicehiring.domain.model.Provider;

import java.util.List;
import java.util.stream.Collectors;

public class ProviderFilterEngine {

    public List<Provider> filter(List<Provider> providers, Specification spec) {
        return providers.stream()
                .filter(spec::isSatisfiedBy)
                .collect(Collectors.toList());
    }
}
